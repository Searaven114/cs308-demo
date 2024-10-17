package com.borau.cs308demo.product.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private String title;
    private String categoryId;
    private String brand;
    private String model;
    private String serialNumber;
    private String description;
    private int quantityInStock;
    private double basePrice;
    private boolean warrantyStatus;
    private String distributorId;
}

