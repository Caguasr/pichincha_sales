package com.pichincha.sales.serviceImpl;

import com.pichincha.sales.entity.CustomerEntity;
import com.pichincha.sales.entity.OrderEntity;
import com.pichincha.sales.repository.ICustomerRepository;
import org.junit.jupiter.api.Assertions;
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
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class CustomerServiceImplTest {
    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    ICustomerRepository customerRepository;

    private CustomerEntity customer;
    private List<OrderEntity> orders;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        customer = new CustomerEntity(1l, "Carlos Aguas", "1234567890", "+593995411607", orders);
    }

    @Test
    void getAll() {
        Mockito.when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
        Assertions.assertNotNull(customerService.getAll());
    }
}