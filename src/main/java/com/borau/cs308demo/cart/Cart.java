package com.borau.cs308demo.cart;

import com.borau.cs308demo.cartitem.CartItem;
import com.borau.cs308demo.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private User user;
    private List<CartItem> cartItems;

    public Cart(User user, List<CartItem> cartItems) {
        this.user = user;
        this.cartItems = cartItems;
    }
}
