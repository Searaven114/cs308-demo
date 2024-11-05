package com.borau.cs308demo.salesmanager;

import com.borau.cs308demo.notification.NotificationService;
import com.borau.cs308demo.product.Product;
import com.borau.cs308demo.product.ProductRepository;
import com.borau.cs308demo.product.ProductService;
import com.borau.cs308demo.product.dto.ProductDTO;
import com.borau.cs308demo.product.exception.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@RequestMapping("/api/sm")
@RestController
public class SalesManagerController {

    private final SalesManagerService salesManagerService;
    private final ProductService productService;
    private final ProductRepository productRepo;
    private final NotificationService notificationService;



    // The sales managers are responsible for setting the prices of the products. TAMAM

    // They shall set a discount on the selected items. When the "discount rate" and the "products" are given, the system automatically sets the new price and notify the users, whose wish list include the discounted product, about the discount.

    // They shall also view all the invoices in a given date range, can print them or save them as “pdf” files.

            // bir şekilde invoiceleri saklamamız lazım db de, id, isim, date, ve multipart falan koyacaz herhalde

    // Last but not least, they shall calculate the revenue and loss/profit in between given dates and view a chart of it.

            //loss u nasıl simüle edecez aq, chart kısmı kolay, vardır kütüphanesi



    @Secured({"ROLE_PRODUCTMANAGER", "ROLE_ADMIN"})
    @PostMapping("/product/{id}/update-price/{price}")
    public ResponseEntity<?> updateProductPrice(@PathVariable String id, @PathVariable double price) {

        try {
            if (price <= 0){
                return ResponseEntity.badRequest().body("Price cannot be equal or lower than 0");
            }

            Product updatedProduct = productService.updateProductPrice(id, price);

            //TODO BURADA NOTIFICATION SERVİSİNİN ÇAĞRILMASI LAZIM FIYAT DEGISIMINI HABER ETMEK ICIN
            //notificationService.notifyUsersAboutPriceChange(...);


            return ResponseEntity.ok(updatedProduct);

        } catch (ProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }


    @Secured({"ROLE_SALESMANAGER", "ROLE_ADMIN"})
    @PostMapping("/product/{id}/apply-discount/{discountRate}")
    public ResponseEntity<?> applyDiscount(@PathVariable String id, @PathVariable double discountRate) {

        try {
            if (discountRate < 0 || discountRate > 100) {
                return ResponseEntity.badRequest().body("Discount rate must be between 0 and 100.");
            }

            Product discountedProduct = productService.applyDiscount(id, discountRate);

            //TODO BURADA NOTIFICATION SERVİSİNİN ÇAĞRILMASI LAZIM DISCOUNTU HABER ETMEK ICIN
            //notificationService.notifyUsersAboutDiscount(...);

            return ResponseEntity.ok(discountedProduct);

        } catch (ProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }











}
