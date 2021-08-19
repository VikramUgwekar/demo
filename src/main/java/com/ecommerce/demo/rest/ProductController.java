package com.ecommerce.demo.rest;

import com.ecommerce.demo.entity.Product;
import com.ecommerce.demo.repo.ProductRepository;
import com.ecommerce.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

        private final Logger log = LoggerFactory.getLogger(ProductController.class);


        private final ProductService productService;

        private final ProductRepository productRepository;

        public ProductController(ProductService productService, ProductRepository productRepository) {
            this.productService = productService;
            this.productRepository = productRepository;
        }


        @PostMapping("/products")
        public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) throws URISyntaxException {
            log.debug("REST request to save Product : {}", product);
            if (product.getId() != null) {
                throw new RestClientException(HttpStatus.BAD_REQUEST.toString());
            }
            Product result = productService.save(product);
            return ResponseEntity
                    .created(new URI("/api/products/" + result.getId()))
                    .body(result);
        }

        @GetMapping("/products")
        public ResponseEntity<List<Product>> getAllProducts(Pageable pageable) {
            log.debug("REST request to get a page of Products");
            Page<Product> page = productService.findAll(pageable);
            return ResponseEntity.ok().body(page.getContent());
        }

        @GetMapping("/products/{id}")
        public ResponseEntity<Product> getProduct(@PathVariable Long id) {
            log.debug("REST request to get Product : {}", id);
            Optional<Product> product = productService.findOne(id);
            return ResponseEntity.ok().body(product.get());
        }

        @DeleteMapping("/products/{id}")
        public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
            log.debug("REST request to delete Product : {}", id);
            productService.delete(id);
            return ResponseEntity
                    .noContent()
                    .build();
        }
}

