package com.ecommerce.demo.service;

import com.ecommerce.demo.entity.Product;
import com.ecommerce.demo.repo.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product save(Product product) {
        log.debug("Request to save Product : {}", product);
        return productRepository.save(product);
    }


    public Optional<Product> partialUpdate(Product product) {
        log.debug("Request to partially update Product : {}", product);

        return productRepository
                .findById(product.getId())
                .map(
                        existingProduct -> {
                            if (product.getProductName() != null) {
                                existingProduct.setProductName(product.getProductName());
                            }
                            if (product.getShortDescription() != null) {
                                existingProduct.setShortDescription(product.getShortDescription());
                            }
                            if (product.getPrice() != null) {
                                existingProduct.setPrice(product.getPrice());
                            }

                            return existingProduct;
                        }
                )
                .map(productRepository::save);
    }


    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id);
    }


    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }
}
