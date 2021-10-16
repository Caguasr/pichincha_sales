package com.pichincha.sales.service;

import com.pichincha.sales.entity.ProductEntity;

import java.util.List;

public interface IProductService {

    public ProductEntity save(ProductEntity product);

    public List<ProductEntity> getAll();

    public ProductEntity getById(Long id);

    public ProductEntity delete(Long id);
}
