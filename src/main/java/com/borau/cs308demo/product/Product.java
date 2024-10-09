package com.borau.cs308demo.product;

import com.borau.cs308demo.category.Category;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "products")
public class Product {

    @Id
    private String id; //6701927e78c5a70ef8d3a4e4
    private String title; //Asus VG259QM Monitor
    private Category category; //Monitor
    private String brand; //Asus
    private String model; //VG259QM
    private String serialNumber; //V551712
    private String description; //Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras molestie urna ex. Phasellus eu justo bibendum, viverra tellus elementum, condimentum nulla. Nam eleifend est dolor, nec pellentesque orci elementum vel.
    private int quantityInStock; //44
    private double basePrice; //400.0
    private boolean warrantyStatus; //2
    private String distributor; //Vatan

    // Bunu 2 parametreye bağlayabiliriz, 1) Satın alım miktari  2) kaç tane wishlistte oldugu ama biz 1 tanesini yapsak yeter, aksiyona gerek yok.
    // Eğer sadece kaç kere alındğına baglı olacaksa direkt bunu alım adedi ile bir tutabiliriz, her alım oldugunda +1 olur her iade gerçekleştiğinde -1 olur bu puan.
    private double popularityPoint;


    public Product(String name, Category category, String brand, String model, String serialNumber, String description, int quantityInStock, double basePrice, boolean warrantyStatus, String distributor) {
        this.title = name;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.description = description;
        this.quantityInStock = quantityInStock;
        this.basePrice = basePrice;
        this.warrantyStatus = warrantyStatus;
        this.distributor = distributor;
    }

    public Product(String id, String name, Category category, String brand, String model, String serialNumber, String description, int quantityInStock, double basePrice, boolean warrantyStatus, String distributor) {
        this.id = id;
        this.title = name;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.description = description;
        this.quantityInStock = quantityInStock;
        this.basePrice = basePrice;
        this.warrantyStatus = warrantyStatus;
        this.distributor = distributor;
    }
}






/*
A product should have the following properties at the very least:
ID,
name,
model,
serialnumber,
description,
quantity in stocks,
price,
warranty status,
distributor information
*/

