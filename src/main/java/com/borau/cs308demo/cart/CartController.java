package com.borau.cs308demo.cart;

import com.borau.cs308demo.cartitem.*;
import com.borau.cs308demo.product.Product;
import com.borau.cs308demo.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Log4j2
@RequestMapping("/api")
@Service
public class CartController {

    private static UserService userService;
    //private static paymentService;


//    @PostMapping("/cart/add/{productId}")

//    @DeleteMapping("/cart/remove/{cartItemId}")

//    @GetMapping("/cart")

//    @PostMapping("/cart/product/quantity/{cartItemId}/{value}")

//    checkout







}
