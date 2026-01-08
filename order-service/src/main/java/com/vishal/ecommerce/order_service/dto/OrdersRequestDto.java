package com.vishal.ecommerce.order_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrdersRequestDto {

    private Long id;
    private List<OrderRequestItemDto> items;
    private BigDecimal totalPrice;
}
