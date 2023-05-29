package com.microservice.rishi.inventory_service.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.rishi.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;
	
	@Transactional(readOnly = true)
	public boolean inStock(String skuCode) {
		
			return inventoryRepository.findBySkuCode(skuCode).isPresent();
		}
	}

