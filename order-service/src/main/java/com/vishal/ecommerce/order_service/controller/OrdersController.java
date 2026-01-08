package com.vishal.ecommerce.order_service.controller;

import com.vishal.ecommerce.order_service.dto.OrdersRequestDto;
import com.vishal.ecommerce.order_service.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;


    @GetMapping("/helloOrders")
    public String helloOrders() {
        return "Hello from oders service";
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
