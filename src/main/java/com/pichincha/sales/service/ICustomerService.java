package com.pichincha.sales.service;

import com.pichincha.sales.entity.CustomerEntity;

import java.util.List;

public interface ICustomerService {
    public List<CustomerEntity> getAll();
}
