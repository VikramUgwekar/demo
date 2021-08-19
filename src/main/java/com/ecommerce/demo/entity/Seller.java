package com.ecommerce.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    long sellerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sellerId", referencedColumnName = "id")
    SellerDetails sellerDetails;

    @Column
    int rating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public SellerDetails getSellerDetails() {
        return sellerDetails;
    }

    public void setSellerDetails(SellerDetails sellerDetails) {
        this.sellerDetails = sellerDetails;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
