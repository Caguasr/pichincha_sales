package com.pichincha.sales.service;

import com.pichincha.sales.entity.ProductEntity;
import com.pichincha.sales.entity.SupplierEntity;

import java.util.List;

public interface IProductService {

    public ProductEntity save(ProductEntity product);

    public List<ProductEntity> getAll();

    public ProductEntity getById(Long id);

    public void delete(Long id);

    public List<ProductEntity> getProductStock(SupplierEntity supplier);


}
