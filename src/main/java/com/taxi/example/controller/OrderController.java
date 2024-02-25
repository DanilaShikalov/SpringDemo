package com.taxi.example.controller;

import com.taxi.example.dto.OrderRequest;
import com.taxi.example.dto.OrderResponse;
import com.taxi.example.service.OrderService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
@Api(tags = "Методы заказов")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/send")
    public OrderResponse sendOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.sendOrder(orderRequest);
    }
}
