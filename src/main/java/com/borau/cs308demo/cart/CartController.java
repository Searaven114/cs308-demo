package com.borau.cs308demo.cart;

import com.borau.cs308demo.cartitem.*;
import com.borau.cs308demo.product.Product;
import com.borau.cs308demo.product.exception.ProductNotFoundException;
import com.borau.cs308demo.user.User;
import com.borau.cs308demo.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Log4j2
@RequestMapping("/api/user")
@RestController
public class CartController {

    private final UserService userService;
    private final CartService cartService;
    //private static paymentService;

    //TODO, calısmıyor.
//    @GetMapping("/cart")
//    public ResponseEntity<?> fetchCart() {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) auth.getPrincipal();
//
//
//        List<CartItem> result = cartService.getAllCartItems(user.getId());
//        log.info("result : {}", result);
//
//        if (result.isEmpty()) {
//            return ResponseEntity.ok().body(result);
//        }
//
//        return ResponseEntity.ok().body(result);
//    }


    @PostMapping("/cart/add")
    public ResponseEntity<String> addProductToCart(@RequestParam String productId, @RequestParam int quantity) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        try {
            cartService.addItemToUserCart(user.getId(), productId, quantity);
            return ResponseEntity.ok("Product added to cart successfully."); //bu responseleri generalize etmek lazım, çok varyasyon var

        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body("Product not found.");

        } catch (Exception e) {
            log.error("Error adding product to cart", e);
            return ResponseEntity.status(500).body("An error occurred while adding the product to the cart.");
        }
    }


//    @DeleteMapping("/cart/remove/{cartItemId}")

//    @PostMapping("/cart/product/quantity/{cartItemId}/{value}")

//    checkout (önce orderi servisi yazılmalı)






}
