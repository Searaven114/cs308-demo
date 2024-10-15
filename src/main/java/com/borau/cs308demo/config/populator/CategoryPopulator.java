package com.borau.cs308demo.config.populator;

import com.borau.cs308demo.cart.CartRepository;
import com.borau.cs308demo.category.Category;
import com.borau.cs308demo.category.CategoryRepository;
import com.borau.cs308demo.distributor.DistributorRepository;
import com.borau.cs308demo.product.ProductRepository;
import com.borau.cs308demo.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Component
public class CategoryPopulator {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;
    private final ProductRepository productRepo;
    private final DistributorRepository distributorRepo;
    private final CategoryRepository categoryRepo;
    private final CartRepository cartRepo;


    @PostConstruct
    public void init() {

        categoryRepo.deleteAll();

        List<Category> categories = Arrays.asList(

                new Category("1", "Laptop", true),
                new Category("2", "Monitor", true),
                new Category("3", "Keyboard", true),
                new Category("4", "Mouse", true)
        );

        categoryRepo.saveAll(categories);

    }
}
