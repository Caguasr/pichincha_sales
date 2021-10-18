package com.pichincha.sales.serviceImpl;

import com.pichincha.sales.entity.ProductEntity;
import com.pichincha.sales.entity.SupplierEntity;
import com.pichincha.sales.repository.ISupplierRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class SupplierServiceImplTest {


    @Mock
    ISupplierRepository supplierRepository;

    @InjectMocks
    SupplierServiceImpl supplierService;

    private SupplierEntity supplierEntity;
    private List<ProductEntity> productEntityList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        supplierEntity = new SupplierEntity(1l, "123456789", "Carlos Aguas", 1, productEntityList, new Date(), new Date());
    }

    @Test
    void save() {
        Mockito.when(supplierRepository.save(supplierEntity)).thenReturn(supplierEntity);
        assertNotNull(supplierService.save(supplierEntity));
    }

    @Test
    void getAll() {
        Mockito.when(supplierRepository.findAll()).thenReturn(Arrays.asList(supplierEntity));
        assertNotNull(supplierService.getAll());
    }

    @Test
    void getById() {
        Mockito.when(supplierRepository.findById(1l)).thenReturn(Optional.ofNullable(supplierEntity));
        assertNotNull(supplierService.getById(1L));
    }

    @Test
    void alreadyUserCreate() {
        Mockito.when(supplierRepository.alreadyUserCreated("1234567890")).thenReturn(supplierEntity);
        assertNull(supplierService.alreadyUserCreate("12345678"));
    }
}