package com.ecommerce.demo.repo;

import com.ecommerce.demo.entity.ShoppingCart;
import com.ecommerce.demo.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findFirstByCustomerDetailsUserLoginAndStatusOrderByIdAsc(OrderStatus orderStatus);

    List<ShoppingCart> findAllByCustomerDetailsUserLoginAndStatusNot(String user, OrderStatus orderStatus);

    Optional<ShoppingCart> findOneByPaymentModificationReference(String paymentRef);

    Optional<ShoppingCart> findOneByPaymentReference(String paymentRef);
}
