package com.borau.cs308demo.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private String userId;
    private String orderDate;
    private int amount; // total fiyat
    private OrderStatus orderStatus;
    //private Set<OrderItem> orderItems = new HashSet<>(); //mesela: ( {msi monitör, 2 adet, 20% indirim}, {asus gpu, 1 adet, 0½ indirim} )


}






