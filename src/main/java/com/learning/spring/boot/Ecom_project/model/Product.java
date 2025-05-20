package com.learning.spring.boot.Ecom_project.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name;
    private String desc;
    private String brand;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private Boolean availability ;
    private int quantity;


}
