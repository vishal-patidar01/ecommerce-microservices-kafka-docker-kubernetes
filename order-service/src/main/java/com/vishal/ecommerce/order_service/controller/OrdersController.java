package com.vishal.ecommerce.order_service.controller;

import com.vishal.ecommerce.order_service.client.InventoryOpenFeignClient;
import com.vishal.ecommerce.order_service.dto.OrdersRequestDto;
import com.vishal.ecommerce.order_service.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/core")
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/helloOrders")
    public String helloOrders(@RequestHeader("X-User-Id") Long userId) {
        return "Hello from orders service, user id is :"+userId;
    }

    @PostMapping("/create-orders")
    public ResponseEntity<OrdersRequestDto> createOrder(@RequestBody OrdersRequestDto ordersRequestDto) {
        OrdersRequestDto ordersRequestDto1 = ordersService.createOrder(ordersRequestDto);
        return ResponseEntity.ok(ordersRequestDto1);
    }


    @GetMapping
    public ResponseEntity<List<OrdersRequestDto>> getAllOrders(HttpServletRequest httpServletRequest) {
        log.info("Fetching all orders via controller");
        List<OrdersRequestDto> order = ordersService.getAllOrders();
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersRequestDto> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with ID: {} via controller", id);
        OrdersRequestDto order = ordersService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
