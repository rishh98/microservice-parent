package com.microservice.rishi.inventory_service.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.rishi.inventory_service.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	


	List<Inventory> findBySkuCodeIn(List<String> skuCode);

}
