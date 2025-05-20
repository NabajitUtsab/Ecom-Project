package com.learning.spring.boot.Ecom_project.service;

import com.learning.spring.boot.Ecom_project.model.Product;
import com.learning.spring.boot.Ecom_project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getALlProducts(){
        return repo.findAll();
    }
}
