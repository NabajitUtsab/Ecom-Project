package com.learning.spring.boot.Ecom_project.controller;

import com.learning.spring.boot.Ecom_project.model.Product;
import com.learning.spring.boot.Ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getALlProducts(),HttpStatus.OK) ;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product theProduct = productService.getProductById(id);
        if(theProduct!=null){
            return new ResponseEntity<>(theProduct,HttpStatus.OK);
        }
       else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile)  {

        try{
            Product thisProduct = productService.addProductInApp(product,imageFile);
            return new ResponseEntity<>(thisProduct,HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
