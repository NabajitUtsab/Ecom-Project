package com.learning.spring.boot.Ecom_project.controller;

import com.learning.spring.boot.Ecom_project.model.Product;
import com.learning.spring.boot.Ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
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

    //Geteing all products

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getALlProducts(),HttpStatus.OK) ;
    }

    //Getting  product by id

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

    //Adding product

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

    //Fetching the image

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int id){
        Product theProduct = productService.getProductById(id);
        byte[] imageFile=theProduct.getImageData();

        return  ResponseEntity.ok().
                contentType(MediaType.valueOf(theProduct.getImageType())).
                body(imageFile);

    }


    //Updating the product

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,
                                                @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile){
        try{
            Product theProduct=productService.updateProductById(id,product,imageFile);

            if(theProduct!=null){
                return new ResponseEntity<>("Updated product successfully",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Not updated, please try again",HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return new ResponseEntity<>("Not updated, please try again",HttpStatus.BAD_REQUEST);

        }

    }


    // deleting the product

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product=productService.getProductById(id);

        if(product!=null){
            productService.deleteProductById(id);
            return new ResponseEntity<>("Deleted product",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_ACCEPTABLE);
        }


    }





}
