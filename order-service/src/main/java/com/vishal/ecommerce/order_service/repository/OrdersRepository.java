package com.vishal.ecommerce.order_service.repository;

import com.vishal.ecommerce.order_service.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
