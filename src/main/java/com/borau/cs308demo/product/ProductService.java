package com.borau.cs308demo.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepo;

    public List<Product> getAllProducts(){

         return productRepo.findAll();

    }


    public Product getProductById( String id ) throws Exception {

        Optional<Product> product = productRepo.findById(id);

        return product.orElseThrow(() -> new RuntimeException("Product not found"));

    }

    /*
    public Product updateProductPrice(String id, double newPrice) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setPrice(newPrice);
            productRepo.save(product);
            //notificationService.notifyUsersAboutPriceChange(product);
            return product;
        }
        throw new IllegalArgumentException("Product not found");
    }


    public Product applyDiscount(String id, double discountRate) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            double newPrice = product.getPrice() * (1 - discountRate / 100);
            product.setPrice(newPrice);
            productRepo.save(product);
            //notificationService.notifyUsersAboutDiscount(product);
            return product;
        }
        throw new IllegalArgumentException("Product not found");
    }*/

}
