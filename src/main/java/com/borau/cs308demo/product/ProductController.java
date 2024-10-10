package com.borau.cs308demo.product;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/get-all-products")
    public ResponseEntity<?> getAllProducts(){

        List<Product> products = productService.getAllProducts();
        if (products.isEmpty() || products == null){
            return ResponseEntity.ok("NO PRODUCTS RETURNED");
        } else {
            return ResponseEntity.ok().body(products);
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_SALESMANAGER", "ROLE_CUSTOMER", "ROLE_PRODUCTMANAGER"})
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id){

        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok().body(product);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }


}
