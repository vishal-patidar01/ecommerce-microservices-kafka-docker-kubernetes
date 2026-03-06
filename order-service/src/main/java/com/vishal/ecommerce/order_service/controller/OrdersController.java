package com.vishal.ecommerce.order_service.controller;

import com.vishal.ecommerce.order_service.client.InventoryOpenFeignClient;
import com.vishal.ecommerce.order_service.config.FeaturesEnableConfig;
import com.vishal.ecommerce.order_service.dto.OrdersRequestDto;
import com.vishal.ecommerce.order_service.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RefreshScope
@RequestMapping("/core")
public class OrdersController {

    private final OrdersService ordersService;

    @Value("${my.variable}")
    private String myVariable;
    
    private final FeaturesEnableConfig featuresEnableConfig;

    @GetMapping("/helloOrders")
    public String helloOrders() {

        if(featuresEnableConfig.isUserTrackingEnabled()) {
            return "User tracking enabled wohoo, my variable is: "+myVariable;
        }else {
            return "User tracking disabled awww, my variable is: "+myVariable;
        }
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
