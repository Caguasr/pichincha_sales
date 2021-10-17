package com.pichincha.sales.service;

import com.pichincha.sales.entity.OrderEntity;

import java.util.Date;
import java.util.List;

public interface IOderService {

    public List<OrderEntity> getAll();

    public  List<OrderEntity> getByDate(Date since, Date until);

    public OrderEntity create(OrderEntity order);

    public  void deleteById(Long id);

}
