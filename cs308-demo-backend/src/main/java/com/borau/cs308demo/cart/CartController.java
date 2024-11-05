package com.borau.cs308demo.cart;

import com.borau.cs308demo.cartitem.*;
import com.borau.cs308demo.constants.Strings;
import com.borau.cs308demo.product.Product;
import com.borau.cs308demo.product.exception.ProductNotFoundException;
import com.borau.cs308demo.user.User;
import com.borau.cs308demo.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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


//    @PostMapping("/cart/add")
//    public ResponseEntity<String> addProductToCart(@RequestParam String productId, @RequestParam int quantity) {
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) auth.getPrincipal();
//
//        try {
//            cartService.addItemToUserCart(user.getId(), productId, quantity);
//            return ResponseEntity.ok("Product added to cart successfully."); //bu responseleri generalize etmek lazım, çok varyasyon var
//
//        } catch (ProductNotFoundException e) {
//            return ResponseEntity.badRequest().body("Product not found.");
//
//        } catch (Exception e) {
//            log.error("Error adding product to cart", e);
//            return ResponseEntity.status(500).body("An error occurred while adding the product to the cart.");
//        }
//    }


    /* HENÜZ LOGINSIZ CARTA EKLEME FONKSIYONALİTESİ İMPLEMENTE EDİLMEDİ */
    @PostMapping("/cart/add")
    public ResponseEntity<String> addProductToCart(@RequestParam String productId, @RequestParam int quantity) {

        // Retrieve the authenticated user from the security context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication object is null or not authenticated
        if (auth == null || !auth.isAuthenticated()) {
            log.warn("Unauthorized access attempt: No authentication found or user not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        // Ensure that the principal is of type User
        if (!(auth.getPrincipal() instanceof User)) {
            log.warn("Unauthorized access attempt: Invalid principal type for user: {}", auth.getPrincipal());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user type");
        }

        // Cast the principal to the User class
        User user = (User) auth.getPrincipal();

        // Double-check if the user object is null
        if (user == null) {
            log.error("Unexpected error: Authenticated principal is null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticated user not found");
        }

        String result = cartService.addItemToUserCart(user.getId(), productId, quantity);


//        if (Strings.PRODUCT_NOT_FOUND.equals(result)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
//
//        } else if (result.startsWith(String.format(Strings.PRODUCT_NOT_AVAILABLE_IN_REQUESTED_QUANTITY, 0))) {
//            return ResponseEntity.badRequest().body(result);
//
//        } else if (Strings.PRODUCT_OUT_OF_STOCK.equals(result)) {
//            return ResponseEntity.badRequest().body(result);
//
//        } else {
//            return ResponseEntity.ok(result);
//        }

        if (Strings.PRODUCT_NOT_FOUND.equals(result)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);

        } else if (Strings.PRODUCT_OUT_OF_STOCK.equals(result)) {
            return ResponseEntity.badRequest().body(result);

        } else if (result.startsWith(String.format(Strings.PRODUCT_NOT_AVAILABLE_IN_REQUESTED_QUANTITY, 0))) {
            return ResponseEntity.badRequest().body(result);

        } else {
            return ResponseEntity.ok(result);
        }
    }



//    @DeleteMapping("/cart/remove/{cartItemId}")

//    checkout (önce orderi servisi yazılmalı)






}
