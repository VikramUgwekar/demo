package com.ecommerce.demo.entity;

import com.ecommerce.demo.entity.enums.OrderStatus;
import com.ecommerce.demo.entity.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "placed_date", nullable = false)
    private Instant placedDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "payment_reference")
    private String paymentReference;

    @Column(name = "payment_modification_reference")
    private String paymentModificationReference;

    @OneToMany(mappedBy = "cart")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "cart" }, allowSetters = true)
    private Set<ProductOrder> orders = new HashSet<>();

    public ShoppingCart() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(Instant placedDate) {
        this.placedDate = placedDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getPaymentModificationReference() {
        return paymentModificationReference;
    }

    public void setPaymentModificationReference(String paymentModificationReference) {
        this.paymentModificationReference = paymentModificationReference;
    }

    public Set<ProductOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<ProductOrder> orders) {
        this.orders = orders;
    }

    public void calculateTotalPrice() {
        if (null != this.orders) {
            this.setTotalPrice(this.orders.stream().map(ProductOrder::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        }
    }


    public ShoppingCart addOrder(ProductOrder productOrder) {
        this.orders.add(productOrder);
        productOrder.setCart(this);
        calculateTotalPrice();
        return this;
    }

    public ShoppingCart(@NotNull final Instant placedDate, @NotNull final OrderStatus status, @NotNull @DecimalMin(value = "0") final BigDecimal totalPrice, @NotNull final PaymentMethod paymentMethod) {
        this.placedDate = placedDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }
}
