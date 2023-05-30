package com.microservice.rishi.order_service.service;

import com.microservice.rishi.order_service.dto.OrderLineItemsDto;
import com.microservice.rishi.order_service.dto.OrderRequest;
import com.microservice.rishi.order_service.model.Order;
import com.microservice.rishi.order_service.model.OrderLineItems;
import com.microservice.rishi.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webclient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);

        //Call Inventory service , and place order if product is in stock
        Boolean result = webclient.get().uri("http://localhost:8082/api/inventory").retrieve().bodyToMono(Boolean.class).block();
        //.block is to make webclient make a asynchronous request to the URL(inventory endpoint)


        if (result) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock..try again later");
        }


    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
