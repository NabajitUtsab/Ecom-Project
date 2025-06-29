package com.learning.spring.boot.Ecom_project.repo;

import com.learning.spring.boot.Ecom_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT p from Product p where " +
            "LOWER( p.name) LIKE lower(concat('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE lower(concat('%', :keyword, '%') ) OR " +
            "LOWER( p.brand) LIKE lower(concat('%' ,:keyword, '%') ) OR " +
            "LOWER( p.category) LIKE lower(concat('%' ,:keyword, '%') )")



    public List<Product> searchProduct(String keyword);
}
