package com.vishal.ecommerce.order_service.client;


import com.vishal.ecommerce.order_service.dto.OrdersRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", path = "/inventory")
public interface InventoryOpenFeignClient {

    @PutMapping("/products/reduce-stocks")
    Double reduceStock(@RequestBody OrdersRequestDto ordersRequestDto);
}
