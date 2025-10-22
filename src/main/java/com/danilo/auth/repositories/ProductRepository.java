package com.danilo.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danilo.auth.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
