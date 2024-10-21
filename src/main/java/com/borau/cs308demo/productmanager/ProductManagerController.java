package com.borau.cs308demo.productmanager;

import com.borau.cs308demo.category.Category;
import com.borau.cs308demo.category.CategoryService;
import com.borau.cs308demo.category.dto.CategoryDTO;
import com.borau.cs308demo.product.Product;
import com.borau.cs308demo.product.ProductRepository;
import com.borau.cs308demo.product.ProductService;
import com.borau.cs308demo.product.dto.ProductDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pm")
@AllArgsConstructor
@Log4j2
public class ProductManagerController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductRepository productRepo;

//    The product managers shall add/remove products as well as product categories, and manage the stocks.

//    Everything related to stock shall be done by the product manager.

//    The product manager is also in the role of delivery department since it controls the stock.
//    This means, the product manager shall view the invoices, products to be delivered, and the corresponding addresses for delivery.
//      A delivery list has the following properties:
//          delivery ID,
//          customer ID,
//          product ID,
//          quantity,
//          total price,
//          delivery address,
//          and a field showing whether the delivery has been completed or not.

//    Last but not least, the product managers shall approve or disapprove the comments. (10%)

    //━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━KATEGORI KISMI━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━//


    @Secured({"ROLE_ADMIN", "ROLE_PRODUCTMANAGER"})
    @GetMapping("/get-categories")
    public ResponseEntity<?> getCategories() {

        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }



    //TODO bu direkt Category türünde almamalı sanırım, dto alacak sekilde bu ve servicedeki update metodu degismeli (sanıırm)
    @Secured({"ROLE_ADMIN", "ROLE_PRODUCTMANAGER"})
    @GetMapping("/update-category/{id}")
    public ResponseEntity<?> updateCategory (@PathVariable String id, @RequestBody Category category) {

        return ResponseEntity.ok().body( categoryService.update(id, category));

    }


    //━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━PRODUCT KISMI━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━//

    @Secured({"ROLE_ADMIN", "ROLE_PRODUCTMANAGER"})
    @PostMapping(value = "/product/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct (@RequestBody @Valid ProductDTO  productDTO) {

        try {
            Product newProduct = productService.addProduct(productDTO);

            return ResponseEntity.ok(newProduct);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    //ChangeStock endpointi



    //━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━xxxxx KISMI━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━//


}
