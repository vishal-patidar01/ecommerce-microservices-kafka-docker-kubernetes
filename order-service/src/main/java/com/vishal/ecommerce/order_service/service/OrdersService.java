package com.vishal.ecommerce.order_service.service;

import com.vishal.ecommerce.order_service.client.InventoryOpenFeignClient;
import com.vishal.ecommerce.order_service.dto.OrdersRequestDto;
import com.vishal.ecommerce.order_service.entity.OrderItem;
import com.vishal.ecommerce.order_service.entity.OrderStatus;
import com.vishal.ecommerce.order_service.entity.Orders;
import com.vishal.ecommerce.order_service.repository.OrdersRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;
    private final InventoryOpenFeignClient inventoryOpenFeignClient;



    public List<OrdersRequestDto> getAllOrders() {
        log.info("Fetching all order");
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrdersRequestDto.class))
                .toList();
    }

    public OrdersRequestDto getOrderById(Long id) {
        log.info("Fetching order with id: {}", id);
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        return modelMapper.map(orders, OrdersRequestDto.class);
    }


//    @Retry(name = "inventoryRetry", fallbackMethod = "createOrderFallback")
//    @RateLimiter(name = "inventoryRateLimiter", fallbackMethod = "createOrderFallback")
    @CircuitBreaker(name = "inventoryCircuitBreaker", fallbackMethod = "createOrderFallback")
    public OrdersRequestDto createOrder(OrdersRequestDto ordersRequestDto) {
        log.info("Calling the createOrder method");
        Double totalPrice = inventoryOpenFeignClient.reduceStock(ordersRequestDto);

        Orders orders = modelMapper.map(ordersRequestDto, Orders.class);
        for(OrderItem orderItem: orders.getItems()) {
            orderItem.setOrder(orders);
        }

        orders.setTotalPrice(totalPrice);
        orders.setOrderStatus(OrderStatus.CONFIRMED);

        Orders savedOrder = ordersRepository.save(orders);

        return modelMapper.map(savedOrder, OrdersRequestDto.class);
    }

    public OrdersRequestDto createOrderFallback(OrdersRequestDto ordersRequestDto, Throwable throwable) {
        log.error("Fallback occurred deu to : {}", throwable.getMessage() );

        return new  OrdersRequestDto();
    }
}

















