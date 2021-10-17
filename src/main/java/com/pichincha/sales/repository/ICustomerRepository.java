package com.pichincha.sales.repository;

import com.pichincha.sales.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
