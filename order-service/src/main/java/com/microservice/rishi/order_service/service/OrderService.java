package com.microservice.rishi.order_service.service;

import com.microservice.rishi.order_service.dto.InventoryResponse;
import com.microservice.rishi.order_service.dto.OrderLineItemsDto;
import com.microservice.rishi.order_service.dto.OrderRequest;

import com.microservice.rishi.order_service.model.Order;
import com.microservice.rishi.order_service.model.OrderLineItems;
import com.microservice.rishi.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webclient;


    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

       List<String> skuCodes=order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        //call Inventory Service and place order if product is in stock
        InventoryResponse[] inventoryResponseArray=webclient.get().uri("http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        //here webclient will make a http request to 8082 and will expect a Boolean in return

        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
        //here allMatch() method checks whether isInStock is true or not...if even one product is false allProductsInStock will be false

        if(allProductsInStock)
            orderRepository.save(order);
        else
            throw new IllegalArgumentException("All Products are not in stock, please try again later");

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
