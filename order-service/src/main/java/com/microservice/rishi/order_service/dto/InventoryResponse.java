package com.microservice.rishi.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*this class is created in order_service because
  after we give a call to Inventory Service to check inventory of a product we get
  a "List".............and this List needs to be handled by order_service ,thats why!*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {
    public String skuCode;
    private boolean isInStock;
}
