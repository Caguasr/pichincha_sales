package com.pichincha.sales.service;

import com.pichincha.sales.entity.SupplierEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISupplierService {

    public SupplierEntity save(SupplierEntity supplier);

    public List<SupplierEntity> getAll();

    public SupplierEntity getById(Long id);

    public SupplierEntity alreadyUserCreate(String ruc);


}
