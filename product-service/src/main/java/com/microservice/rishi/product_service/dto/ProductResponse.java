package com.microservice.rishi.product_service.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	
	//this class is created seperately because is a good rule, if in future Product class changes we dont have to change here as its not necessary for response to know the changes in Product class
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
}
