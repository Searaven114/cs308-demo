package com.borau.cs308demo.config.populator;

import com.borau.cs308demo.distributor.Distributor;

import java.util.Arrays;
import java.util.List;
//import com.borau.cs308demo.cart.CartRepository;
import com.borau.cs308demo.category.CategoryRepository;
import com.borau.cs308demo.distributor.DistributorRepository;
import com.borau.cs308demo.product.ProductRepository;
import com.borau.cs308demo.user.UserRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class DistributorPopulator {

    private final DistributorRepository distributorRepo;

    private final Faker fake = new Faker();


    @PostConstruct
    public void init() {

        distributorRepo.deleteAll();

        List<Distributor> distributors = Arrays.asList(

                new Distributor(
                        "1",
                        "Aral A.Ş",
                        fake.phoneNumber().phoneNumber(),
                        fake.address().fullAddress(),
                        fake.internet().url(),
                        true
                ),
                new Distributor(
                        "2",
                        "Bircom",
                        fake.phoneNumber().phoneNumber(),
                        fake.address().fullAddress(),
                        fake.internet().url(),
                        true
                )
        );

        distributorRepo.saveAll(distributors);
    }
}
