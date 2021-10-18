package com.pichincha.sales.serviceImpl;

import com.pichincha.sales.entity.ProductEntity;
import com.pichincha.sales.entity.SupplierEntity;
import com.pichincha.sales.repository.IProductRepository;
import com.pichincha.sales.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    @Transactional
    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntity> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductEntity getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntity> getProductStock(SupplierEntity supplier) {
        return productRepository.getProductStock(supplier);
    }


}
