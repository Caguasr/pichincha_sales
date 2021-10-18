package com.pichincha.sales.serviceImpl;

import com.pichincha.sales.entity.ProductEntity;
import com.pichincha.sales.entity.SupplierEntity;
import com.pichincha.sales.repository.IProductRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class ProductServiceImplTest {

    @Mock
    IProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    private ProductEntity productEntity;
    private List<ProductEntity> list;
    private SupplierEntity supplierEntity;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Mockito.when(productRepository.save(productEntity)).thenReturn(productEntity);
        assertNull(productService.save(productEntity));
    }

    @Test
    void getAll() {
        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(productEntity));
        assertNotNull(productService.getAll());
    }

    @Test
    void getById() {
        Mockito.when(productRepository.findById(1l)).thenReturn(Optional.ofNullable(productEntity));
        assertNull(productService.getById(1L));
    }

    @Test
    void getProductStock() {
        Mockito.when(productRepository.getProductStock(supplierEntity)).thenReturn(Arrays.asList(productEntity));
        assertNotNull(productService.getProductStock(supplierEntity));
    }
}