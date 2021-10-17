package com.pichincha.sales.serviceImpl;

import com.pichincha.sales.entity.OrderEntity;
import com.pichincha.sales.repository.IOrderRepository;
import com.pichincha.sales.service.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOderService {

    @Autowired
    private IOrderRepository orderRepository;


    @Override
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity create(OrderEntity order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
