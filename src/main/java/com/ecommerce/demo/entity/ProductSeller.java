package com.ecommerce.demo.entity;

import javax.persistence.*;

@Table(name = "ProductSeller")
@Entity
public class ProductSeller {


    int id;

    @ManyToOne
    @JoinColumn(name="productId", nullable=false)
    Product product;

    @Column
    int sellerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sellerId", referencedColumnName = "id")
    Seller seller;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
