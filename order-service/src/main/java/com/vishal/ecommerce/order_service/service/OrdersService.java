package com.vishal.ecommerce.order_service.service;

import com.vishal.ecommerce.order_service.client.InventoryOpenFeignClient;
import com.vishal.ecommerce.order_service.dto.OrdersRequestDto;
import com.vishal.ecommerce.order_service.entity.OrderItem;
import com.vishal.ecommerce.order_service.entity.OrderStatus;
import com.vishal.ecommerce.order_service.entity.Orders;
import com.vishal.ecommerce.order_service.repository.OrdersRepository;
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

    public OrdersRequestDto createOrder(OrdersRequestDto ordersRequestDto) {
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
}

















