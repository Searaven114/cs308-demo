package com.borau.cs308demo.cart;

import com.borau.cs308demo.cartitem.CartItem;
import com.borau.cs308demo.user.User;
import jakarta.annotation.Nonnull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "carts")
public class Cart {

    @Id
    private String id;

    //private User user;

    @Nonnull
    private String userId;

    private List<CartItem> cartItems = new ArrayList<>();

    private Double TotalPrice;

    public Cart(@Nonnull String userId, List<CartItem> cartItems) {
        this.userId = userId;
        this.cartItems = cartItems;
    }
}
