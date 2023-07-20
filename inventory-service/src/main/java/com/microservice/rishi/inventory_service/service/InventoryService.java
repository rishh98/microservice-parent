package com.microservice.rishi.inventory_service.service;


import com.microservice.rishi.inventory_service.dto.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.rishi.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;
	
	@Transactional(readOnly = true)
	public List<InventoryResponse> inStock(List<String> skuCode) {
		
			return inventoryRepository.findBySkuCodeIn(skuCode).stream()
					.map(inventory ->
						InventoryResponse.builder()
								.skuCode(inventory.getSkuCode())
								.isInStock(inventory.getQuantity()>0)
								.build()
					).toList();
		}
	}

