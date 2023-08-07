package com.microservice.rishi.inventory_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.microservice.rishi.inventory_service.model.Inventory;
import com.microservice.rishi.inventory_service.repository.InventoryRepository;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
    	return args->{
    		Inventory inventory = new Inventory();
    		inventory.setSkuCode("redmi_k20_pro");
    		inventory.setQuantity(100);
    		
    		Inventory inventory1 = new Inventory();
    		inventory1.setSkuCode("redmi_k20_pro_black");
    		inventory1.setQuantity(0);
    		
    		inventoryRepository.save(inventory);
    		inventoryRepository.save(inventory1);
    	};
    }
}
