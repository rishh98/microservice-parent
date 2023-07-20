package com.microservice.rishi.inventory_service.controller;

import com.microservice.rishi.inventory_service.dto.InventoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.rishi.inventory_service.service.InventoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {
	
	private final InventoryService inventoryService;

	//http://localhost:8082/api/inventory/iphone13-red, iphone13-red
	//http://localhost:8082/api/inventory/?sku-code=iphone13-red&sku-code=iphone13-red not desirable input
	//changing String to List in @PathVariable to catch n number of request
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
		return inventoryService.inStock(skuCode);
	}

}
