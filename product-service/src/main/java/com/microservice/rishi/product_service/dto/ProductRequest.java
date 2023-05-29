package com.microservice.rishi.product_service.dto;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="t_product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	
	@Id
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
}
