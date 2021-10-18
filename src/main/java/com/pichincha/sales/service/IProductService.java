package com.pichincha.sales.service;

import com.pichincha.sales.entity.ProductEntity;
import com.pichincha.sales.entity.SupplierEntity;

import java.util.List;

public interface IProductService {

    ProductEntity save(ProductEntity product);

    List<ProductEntity> getAll();

    ProductEntity getById(Long id);

    void delete(Long id);

    List<ProductEntity> getProductStock(SupplierEntity supplier);


}
