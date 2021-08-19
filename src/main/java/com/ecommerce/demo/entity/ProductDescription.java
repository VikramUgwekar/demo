package com.ecommerce.demo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="productDescription")
public class ProductDescription {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String productId;

    String description;

}
