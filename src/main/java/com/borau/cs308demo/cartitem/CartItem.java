package com.borau.cs308demo.cartitem;

import com.borau.cs308demo.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class CartItem {
    @Id
    private String id;
    private Product product;
    private Integer quantity;
}
