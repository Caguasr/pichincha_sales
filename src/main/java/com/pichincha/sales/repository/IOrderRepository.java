package com.pichincha.sales.repository;

import com.pichincha.sales.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select u from OrderEntity u where u.createdAt between ?1 And ?2")
    List<OrderEntity> getOrderByDate(Date since, Date until);
}
