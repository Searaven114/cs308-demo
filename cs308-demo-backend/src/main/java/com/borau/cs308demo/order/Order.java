package com.borau.cs308demo.order;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.borau.cs308demo.cart.Cart;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private Cart cart;
    private OrderStatus orderStatus;
    private Date createdAt;
    private Long total;

}







