package com.pichincha.sales.repository;

import com.pichincha.sales.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
}
