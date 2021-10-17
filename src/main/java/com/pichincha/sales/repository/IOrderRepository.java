package com.pichincha.sales.repository;

import com.pichincha.sales.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
}
