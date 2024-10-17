package com.borau.cs308demo.salesmanager;

import com.borau.cs308demo.category.CategoryRepository;
import com.borau.cs308demo.distributor.DistributorRepository;
import com.borau.cs308demo.product.Product;
import com.borau.cs308demo.product.ProductRepository;
import com.borau.cs308demo.product.ProductService;
import com.borau.cs308demo.product.dto.ProductDTO;
import com.borau.cs308demo.product.exception.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
public class SalesManagerService {

    private final ProductService productService;
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final DistributorRepository distributorRepo;

    // The sales managers are responsible for setting the prices of the products.

    // They shall set a discount on the selected items. When the discount rate and the products are given,
    //     the system automatically sets the new price and notify the users, whose wish list include the discounted product, about the discount.

    // They shall also view all the invoices in a given date range, can print them or save them as “pdf” files.

    // Last but not least, they shall calculate the revenue and loss/profit in between given dates and view a chart of it.

//    @Secured({"ROLE_PRODUCTMANAGER", "ROLE_ADMIN"})
//    public boolean updateProductPrice(String id, double price){
//
//        Optional<Product> optionalProduct = productRepo.findById(id);
//
//        if (optionalProduct.isPresent()){
//            Product p = optionalProduct.get();
//            p.setBasePrice(price);
//            productRepo.save(p);
//
//            return true;
//        } else {
//            throw new ProductNotFoundException("Product with it id " + id + " is not found");
//        }
//    }





}
