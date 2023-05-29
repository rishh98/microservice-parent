package com.microservice.rishi.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.rishi.product_service.model.Product;



public interface ProductRepository extends JpaRepository<Product, String> {

}