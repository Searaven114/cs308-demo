package com.borau.cs308demo.product;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Document(collection = "products")
public class Product {

    @Id
    private String id; //6701927e78c5a70ef8d3a4e4

    @Indexed(unique = true)
    private String title; //Asus VG259QM Monitor

    @Indexed(unique = true)
    private String categoryId; // Reference to the category ID instead of the Category object

    private String brand;           // Asus
    private String model;           // VG259QM
    private String serialNumber;    // V551712
    private String description;     // Lorem ipsum dolor sit amet...
    private int quantityInStock;    // 44
    private double basePrice;       // 400.0
    private boolean warrantyStatus; // 2
    private String distributorId;   // Vatan
    // List<String> commentIds ?

    // Popularity metric, updated based on purchases and wishlist additions(?)
    private double popularityPoint;

    // Constructor without id (used when creating new products)
    public Product(String name, String categoryId, String brand, String model, String serialNumber, String description, int quantityInStock, double basePrice, boolean warrantyStatus, String distributor) {
        this.title = name;
        this.categoryId = categoryId;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.description = description;
        this.quantityInStock = quantityInStock;
        this.basePrice = basePrice;
        this.warrantyStatus = warrantyStatus;
        this.distributorId = distributor;
    }

    // Constructor with id (used when retrieving or updating existing products)
    public Product(String id, String name, String categoryId, String brand, String model, String serialNumber, String description, int quantityInStock, double basePrice, boolean warrantyStatus, String distributor) {
        this.id = id;
        this.title = name;
        this.categoryId = categoryId;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.description = description;
        this.quantityInStock = quantityInStock;
        this.basePrice = basePrice;
        this.warrantyStatus = warrantyStatus;
        this.distributorId = distributor;
    }
}
