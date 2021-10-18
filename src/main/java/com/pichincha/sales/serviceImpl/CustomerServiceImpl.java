package com.pichincha.sales.serviceImpl;

import com.pichincha.sales.entity.CustomerEntity;
import com.pichincha.sales.repository.ICustomerRepository;
import com.pichincha.sales.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerEntity> getAll() {
        return customerRepository.findAll();
    }
}
