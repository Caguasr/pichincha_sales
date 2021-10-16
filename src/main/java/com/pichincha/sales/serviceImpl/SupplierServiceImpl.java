package com.pichincha.sales.serviceImpl;

import com.pichincha.sales.repository.ISupplierRepository;
import com.pichincha.sales.entity.SupplierEntity;
import com.pichincha.sales.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private ISupplierRepository supplierRepository;

    @Override
    public SupplierEntity save(SupplierEntity supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public List<SupplierEntity> getAll() {
        return supplierRepository.findSupplierActive();
    }

    @Override
    public SupplierEntity getById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public SupplierEntity alreadyUserCreate(String ruc) {
        return supplierRepository.alreadyUserCreated(ruc);
    }
}
