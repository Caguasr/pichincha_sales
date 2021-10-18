package com.pichincha.sales.serviceImpl;

import com.pichincha.sales.entity.CustomerEntity;
import com.pichincha.sales.entity.DetailOrderEntity;
import com.pichincha.sales.entity.OrderEntity;
import com.pichincha.sales.repository.IOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class OrderServiceImplTest {

    @Mock
    IOrderRepository orderRepository;
    @InjectMocks
    OrderServiceImpl orderService;

    private OrderEntity order;
    private List<DetailOrderEntity> detailOrder;
    private CustomerEntity customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new OrderEntity(1l, customer, detailOrder, new Date());
    }

    @Test
    void getAll() {
        Mockito.when(orderRepository.findAll()).thenReturn(Arrays.asList(order));
        assertNotNull(orderService.getAll());
    }

    @Test
    void getByDate() {
        Mockito.when(orderRepository.getOrderByDate(new Date(), new Date())).thenReturn(Arrays.asList(order));
        assertNotNull(orderService.getByDate(new Date(), new Date()));
    }

    @Test
    void create() {
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        assertNotNull(orderService.create(order));
    }
}