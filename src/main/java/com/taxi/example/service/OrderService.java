package com.taxi.example.service;

import com.taxi.example.dto.OrderRequest;
import com.taxi.example.dto.OrderResponse;
import com.taxi.example.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {
    private OrderRepository orderRepository;
    private KafkaTemplate<String, String > kafkaTemplate;

    public OrderResponse sendOrder(OrderRequest orderRequest) {
        kafkaTemplate.send("taxi-topic", orderRequest.toString());
        return OrderResponse.builder().source(orderRequest.getSource()).target(orderRequest.getTarget()).date(new Date()).build();
    }

    @KafkaListener(topics = "taxi-topic")
    public void getOrder(String order) {
        log.info(String.format("Get %s order", order));
    }
}
