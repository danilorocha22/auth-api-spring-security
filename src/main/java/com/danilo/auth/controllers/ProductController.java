package com.danilo.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danilo.auth.domain.product.Product;
import com.danilo.auth.domain.product.ProductRequestDTO;
import com.danilo.auth.domain.product.ProductResponseDTO;
import com.danilo.auth.repositories.ProductRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("product")
public class ProductController {

    private ProductRepository repository;

    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody @Valid ProductRequestDTO body) {
        var newProduct = new Product(body);
        this.repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        var productList = this.repository.findAll().stream()
                .map(ProductResponseDTO::new)
                .toList();
        return ResponseEntity.ok(productList);
    }
}
