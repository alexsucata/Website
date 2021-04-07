package com.sda.website.controller;


import com.sda.website.entity.ProductEntity;
import com.sda.website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
//http://localhost:8080/api/....
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{productId}")
    public ProductEntity getProductById(@PathVariable Integer productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @PostMapping("/products/add")
    public ProductEntity addNewProduct(@Valid @RequestBody ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @PutMapping("/products/update/{productId}")
    public ProductEntity updateProduct(@PathVariable Integer productId, @RequestBody ProductEntity productEntity) {
        ProductEntity product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            return productEntity;
        } else {
            productEntity.setName(productEntity.getName());
            productEntity.setDescription(productEntity.getDescription());
            productEntity.setPrice(productEntity.getPrice());
            return productRepository.save(product);
        }
    }

    @DeleteMapping("/products/delete/{productId}")
    public String deleteProduct(@PathVariable Integer productId) {
        //productRepository.deleteById(productId);
        Optional<ProductEntity> product = productRepository.findById(productId);
        if(product.isPresent()){
            productRepository.delete(product.get());
            return "Success";
        }else{
            return "Record not found";
        }

    }

}
