package com.ojas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojas.entity.ProductsEntity;
@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, Long> {

}
