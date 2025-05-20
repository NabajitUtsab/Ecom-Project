package com.learning.spring.boot.Ecom_project.repo;

import com.learning.spring.boot.Ecom_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
