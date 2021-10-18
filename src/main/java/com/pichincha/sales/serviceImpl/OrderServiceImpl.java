package com.pichincha.sales.serviceImpl;

import com.pichincha.sales.entity.OrderEntity;
import com.pichincha.sales.repository.IOrderRepository;
import com.pichincha.sales.service.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOderService {

    @Autowired
    private IOrderRepository orderRepository;


    @Override
    @Transactional(readOnly = true)
    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderEntity> getByDate(Date since, Date until) {
        return orderRepository.getOrderByDate(since, until);
    }


    @Override
    @Transactional
    public OrderEntity create(OrderEntity order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
