package com.ecommerce.demo.entity;

import com.ecommerce.demo.entity.enums.ProductCategory;
import com.ecommerce.demo.entity.enums.ProductStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Table(name="Product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    @Column
    private BigDecimal price;

    @Column
    private String productName;
    @Column
    private String productBrand;
    @Column
    private ProductCategory productCategory;
    @Column
    private String shortDescription;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private ProductDescription productDescription;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductSeller> productSeller;
    @Column
    private ProductStatus productStatus;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    public Set<ProductSeller> getProductSeller() {
        return productSeller;
    }

    public void setProductSeller(Set<ProductSeller> productSeller) {
        this.productSeller = productSeller;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}
