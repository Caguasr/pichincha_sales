package com.pichincha.sales.service;

import com.pichincha.sales.entity.SupplierEntity;

import java.util.List;

public interface ISupplierService {

    SupplierEntity save(SupplierEntity supplier);

    List<SupplierEntity> getAll();

    SupplierEntity getById(Long id);

    SupplierEntity alreadyUserCreate(String ruc);


}
