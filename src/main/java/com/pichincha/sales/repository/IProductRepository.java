package com.pichincha.sales.repository;

import com.pichincha.sales.entity.ProductEntity;
import com.pichincha.sales.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("select p  from ProductEntity p where p.stock > 1 and p.supplier = ?1")
    List<ProductEntity> getProductStock(SupplierEntity supplier);
}
