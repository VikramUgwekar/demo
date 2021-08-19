package com.ecommerce.demo.rest;

import com.ecommerce.demo.entity.ShoppingCart;
import com.ecommerce.demo.repo.ShoppingCartRepository;
import com.ecommerce.demo.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
public class CartController {
    private final ShoppingCartService shoppingCartService;

    private final ShoppingCartRepository shoppingCartRepository;

    public CartController(ShoppingCartService shoppingCartService, ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    private final Logger log = LoggerFactory.getLogger(CartController.class);
    @PutMapping("/shopping-carts/add-product/{id}")
    public ResponseEntity<ShoppingCart> addProduct(@PathVariable Long id) throws EntityNotFoundException {
        log.debug("REST request to add product to ShoppingCart");
        ShoppingCart result = shoppingCartService.addProductForUser(id);
        return ResponseEntity.ok()
                .body(result);
    }
}
