package com.pichincha.sales.repository;

import com.pichincha.sales.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISupplierRepository extends JpaRepository<SupplierEntity, Long> {

    @Query("select s from SupplierEntity s  where s.active = 1")
    List<SupplierEntity> findSupplierActive();

    @Query("select u from SupplierEntity u  where u.ruc = ?1 and u.active = 0")
    SupplierEntity alreadyUserCreated(String ruc);
}
