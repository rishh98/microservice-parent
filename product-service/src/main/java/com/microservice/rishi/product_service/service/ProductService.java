package com.microservice.rishi.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import com.microservice.rishi.product_service.dto.ProductRequest;
import com.microservice.rishi.product_service.dto.ProductResponse;
import com.microservice.rishi.product_service.model.Product;
import com.microservice.rishi.product_service.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;




@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	
//	public ProductService(ProductRepository productRepository) {
//		this.productRepository=productRepository;
//	}
	
	
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder().name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		
		productRepository.save(product);
		log.info("Product {} is saved", product.getId()); //slf4j will inject it into {}
		
		
		
	}


	public List<ProductResponse> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products = productRepository.findAll();
		return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
	}
	
	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder().id(product.getId().toString())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
}
