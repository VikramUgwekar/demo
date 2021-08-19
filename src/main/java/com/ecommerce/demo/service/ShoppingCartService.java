package com.ecommerce.demo.service;

import com.ecommerce.demo.entity.Product;
import com.ecommerce.demo.entity.ProductOrder;
import com.ecommerce.demo.entity.ShoppingCart;
import com.ecommerce.demo.entity.enums.OrderStatus;
import com.ecommerce.demo.entity.enums.PaymentMethod;
import com.ecommerce.demo.repo.ShoppingCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ShoppingCartService {

    private final Logger log = LoggerFactory.getLogger(ShoppingCartService.class);
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;

    }

    public ShoppingCart addProductForUser(Long id) throws EntityNotFoundException {
        ShoppingCart activeCart = findActiveCart();
        Product product = productService.findOne(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        ProductOrder order;
        List<ProductOrder> orders = activeCart.getOrders().stream().filter(productOrder -> productOrder.getProduct().getId().equals(id)).collect(Collectors.toList());
        if (orders.isEmpty()) {
            order = new ProductOrder();
            order.setQuantity(1);
            order.setTotalPrice(product.getPrice());
            order.setProduct(product);
            order.setCart(activeCart);
            activeCart.addOrder(order);
        } else {
            order = orders.get(0);
            order.setQuantity(order.getQuantity() + 1);
            order.setTotalPrice(product.getPrice().multiply(new BigDecimal(order.getQuantity())));
        }
        activeCart.calculateTotalPrice();

        return save(activeCart);
    }
    public ShoppingCart findActiveCart() {
        Optional<ShoppingCart> oCart = shoppingCartRepository.findFirstByCustomerDetailsUserLoginAndStatusOrderByIdAsc(OrderStatus.OPEN);
        ShoppingCart activeCart = oCart.orElseGet(() -> shoppingCartRepository.save(new ShoppingCart(
                Instant.now(), OrderStatus.OPEN, BigDecimal.ZERO, PaymentMethod.CREDIT_CARD
        )));
        log.info("Cart for user {} has {} orders", activeCart.getOrders().size());
        return activeCart;
    }

    public ShoppingCart save(ShoppingCart shoppingCart) {
        log.debug("Request to save ShoppingCart : {}", shoppingCart);
        return shoppingCartRepository.save(shoppingCart);
    }
}
