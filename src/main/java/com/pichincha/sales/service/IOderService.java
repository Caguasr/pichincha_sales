package com.pichincha.sales.service;

import com.pichincha.sales.entity.OrderEntity;

import java.util.Date;
import java.util.List;

public interface IOderService {

    List<OrderEntity> getAll();

    List<OrderEntity> getByDate(Date since, Date until);

    OrderEntity create(OrderEntity order);

    void deleteById(Long id);

}
