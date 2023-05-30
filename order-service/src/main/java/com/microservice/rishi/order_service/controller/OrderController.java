package com.microservice.rishi.order_service.controller;

import com.microservice.rishi.order_service.dto.OrderRequest;
import com.microservice.rishi.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        log.info("Order Placed for {}",orderRequest.getOrderLineItemsDtoList());
        return "Order Placed Successfully";

        /*
        * Sample entry
        * {
    "orderLineItemsDtoList":[
        {
            "skuCode":"redmi_k20_pro",
            "price":"800",
            "quantity":1
        }
    ]
}*/
    }
}
