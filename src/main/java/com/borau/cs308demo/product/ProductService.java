package com.borau.cs308demo.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // Fetch paginated products without sorting
    public Page<Product> getPaginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findAll(pageable);
    }

    // Fetch products sorted by price dynamically by asc/desc
    public Page<Product> getProductsSortedByPrice(int page, int size, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by("basePrice").descending()
                : Sort.by("basePrice").ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepo.findAll(pageable);
    }

    // Fetch products sorted by popularity (descending)
    public Page<Product> getProductsSortedByPopularity(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("popularityPoint").descending());
        return productRepo.findAllByOrderByPopularityPointDesc(pageable);
    }

    public Product getProductById(String id) throws Exception {

        Optional<Product> product = productRepo.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Fetch products filtered by category and sorted by price
    public Page<Product> getProductsByCategoryAndSortedByPrice(String categoryId, int page, int size, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by("basePrice").descending()
                : Sort.by("basePrice").ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        // Find by category and apply pagination with sorting
        return productRepo.findByCategoryId(categoryId, pageable);
    }

    // Method to handle PAGINATED search
    public Page<Product> searchProducts(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query, pageable);
    }


//━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━//

    // "ROLE_SALESMANAGER" ve veya "ROLE_ADMIN" etkileşime girecek bu abi ile
    @Secured({"ROLE_SALESMANAGER", "ROLE_ADMIN"})
    public Product updateProductPrice(String id, double newPrice) {

        Product product = productRepo.findById(id).orElse(null);

        if (product != null) {

            product.setBasePrice(newPrice);

            productRepo.save(product);

            //notificationService.notifyUsersAboutPriceChange(product);

            return product;
        }
        throw new IllegalArgumentException("Product not found");
    }



    // "ROLE_SALESMANAGER" ve veya "ROLE_ADMIN" etkileşime girecek bu abi ile
    @Secured({"ROLE_SALESMANAGER", "ROLE_ADMIN"})
    public Product applyDiscount(String id, double discountRate) {

        Optional<Product> productOptional = productRepo.findById(id);

        if ( productOptional.isPresent() ) {

            Product product = productOptional.get();

            double newPrice = product.getBasePrice() * (1 - discountRate / 100);

            product.setBasePrice( newPrice );

            productRepo.save(product);

            //notificationService.notifyUsersAboutDiscount(product,discountRate);

            return product;
        }
        throw new IllegalArgumentException("Product not found");
    }

}
