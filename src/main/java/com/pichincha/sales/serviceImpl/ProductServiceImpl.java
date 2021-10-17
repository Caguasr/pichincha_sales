package com.pichincha.sales.serviceImpl;

import com.pichincha.sales.entity.ProductEntity;
import com.pichincha.sales.repository.IProductRepository;
import com.pichincha.sales.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
