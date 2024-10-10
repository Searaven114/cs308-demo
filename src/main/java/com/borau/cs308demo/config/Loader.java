package com.borau.cs308demo.config;

import com.borau.cs308demo.address.Address;
import com.borau.cs308demo.category.Category;
import com.borau.cs308demo.category.CategoryRepository;
import com.borau.cs308demo.distributor.Distributor;
import com.borau.cs308demo.distributor.DistributorRepository;
import com.borau.cs308demo.product.Product;
import com.borau.cs308demo.product.ProductRepository;
import com.borau.cs308demo.user.User;
import com.borau.cs308demo.user.UserRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Component
public class Loader {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;
    private final ProductRepository productRepo;
    private final DistributorRepository distributorRepo;
    private final CategoryRepository categoryRepo;

    private final Faker fake = new Faker();


    @PostConstruct
    public void init() {


        // Clean the collection to avoid duplicates on restart
        userRepo.deleteAll();

        // Create fake users with addresses
        List<User> users = Arrays.asList(
                new User("fuat", encoder.encode("avni"), "fuat", "05665127700"),
                new User("admin", encoder.encode("adminpw"), "admin@example.com", fake.phoneNumber().phoneNumber()),
                new User("salesmanager", encoder.encode("salespw"), "sales@example.com", fake.phoneNumber().phoneNumber()),
                new User("productmanager", encoder.encode("productpw"), "product@example.com", fake.phoneNumber().phoneNumber()),
                new User("customer1", encoder.encode("customerpw"), "customer1@example.com", fake.phoneNumber().phoneNumber()),
                new User("customer2", encoder.encode("customerpw"), "customer2@example.com", fake.phoneNumber().phoneNumber()),
                new User("customer3", encoder.encode("customerpw"), "customer3@example.com", fake.phoneNumber().phoneNumber())
        );


        for (User user : users) {
            Set<String> roles = new HashSet<>();

            if ("admin@example.com".equals(user.getUsername())) {
                roles.add("ROLE_ADMIN");
                roles.add("ROLE_SALESMANAGER");
                roles.add("ROLE_PRODUCTMANAGER");
                roles.add("ROLE_CUSTOMER");

            } else if ("sales@example.com".equals(user.getUsername())) {
                roles.add("ROLE_SALESMANAGER");
                roles.add("ROLE_CUSTOMER");

            } else if ("product@example.com".equals(user.getUsername())) {
                roles.add("ROLE_PRODUCTMANAGER");
                roles.add("ROLE_CUSTOMER");
            } else {
                roles.add("ROLE_CUSTOMER");
            }

            //Setting Roles
            user.setRoles(roles);

            //Setting mock IPv4 address
            user.setRegisterDate(LocalDateTime.now().toString());
            user.setRegisterIp(fake.internet().ipV4Address());

            //Setting mock taxId
            String taxId = fake.idNumber().valid();
            user.setTaxId(taxId);

            //Setting mock age
            String age = fake.number().digits(3);
            user.setAge(age);

            List<Address> addresses = new ArrayList<>();

            int number = fake.number().numberBetween(1, 10);

            if (number >= 4) {
                //addresses.add(new Address(fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country()));

            } else if (number < 3) {
                //addresses.add(new Address(fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country()));
                //addresses.add(new Address(fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country(), fake.lorem().characters(30)));

            } else {
                //addresses.add(new Address(fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country()));
                //addresses.add(new Address(fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country()));
                //addresses.add(new Address(fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country(), fake.lorem().characters(25)));
            }

            //Setting mock addresses
            user.setAddresses(addresses);
        }

        //Save users with addresses
        userRepo.saveAll(users);


//━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━//

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

//━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━//

        categoryRepo.deleteAll();

        List<Category> categories = Arrays.asList(

                new Category("1", "Laptop", true),
                new Category("2", "Monitor", true),
                new Category("3", "Keyboard", true),
                new Category("4", "Mouse", true)
        );

        categoryRepo.saveAll(categories);

//━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━//

        productRepo.deleteAll();

        Optional<Category> monitorCat = categoryRepo.findById("2");
        Optional<Category> laptopCat = categoryRepo.findById("1");
        Optional<Category> mouseCat = categoryRepo.findById("4");

        List<Product> products = new ArrayList<>();

        if (monitorCat.isPresent()) {
            // Product 1
            products.add(new Product(
                    "1",
                    "Dell UltraSharp Monitor",
                    monitorCat.get().getId(),  // Retrieve the Category from Optional
                    "Dell",
                    "U2720Q",
                    "D273928Q",
                    "27-inch 4K monitor with vibrant colors and sharp details, ideal for productivity and media consumption.",
                    35,
                    550.99,
                    true,
                    "1"
            ));
        } else {
            throw new IllegalStateException("Category with id '1' is not found.");
        }

        // Add products using predefined categories
        monitorCat.ifPresent(category -> products.add(new Product(
                "2",
                "Dell UltraSharp Monitor",
                monitorCat.get().getId(),
                "Dell",
                "U2720Q",
                "D273928Q",
                "27-inch 4K monitor with vibrant colors and sharp details, ideal for productivity and media consumption.",
                35,
                550.99,
                true,
                "2"
        )));

        laptopCat.ifPresent(category -> products.add(new Product(
                "3",
                "HP Envy Laptop",
                laptopCat.get().getId(),
                "HP",
                "Envy 13",
                "HP3438739E",
                "Slim and powerful 13-inch laptop, with Intel Core i7 processor, 16GB RAM, and 512GB SSD.",
                25,
                1099.99,
                true,
                "2"
        )));

        mouseCat.ifPresent(category -> products.add(new Product(
                "4",
                "Logitech MX Master 3",
                mouseCat.get().getId(),
                "Logitech",
                "MX Master 3",
                "LOGMXM331",
                "Ergonomic wireless mouse with programmable buttons, ideal for productivity and creative workflows.",
                80,
                99.99,
                true,
                "1"
        )));

        laptopCat.ifPresent(category -> {
            products.add(new Product(
                    "5",
                    "MacBook Pro 16-inch",
                    laptopCat.get().getId(),
                    "Apple",
                    "M1 Pro",
                    "MBP16M1P2022",
                    "Apple MacBook Pro with M1 Pro chip, 16-inch Retina Display, 16GB RAM, 512GB SSD.",
                    15,
                    2499.99,
                    true,
                    "1"
            ));
        });

        laptopCat.ifPresent(category1 -> {
            products.add(new Product(
                    "6",
                    "Dell XPS 13",
                    laptopCat.get().getId(),
                    "Dell",
                    "XPS 13",
                    "XPS13-9300",
                    "Dell XPS 13 with Intel Core i7, 16GB RAM, 512GB SSD, and 13-inch 4K display.",
                    30,
                    1499.99,
                    true,
                    "2"
            ));
        });

        monitorCat.ifPresent(category -> {
            products.add(new Product(
                    "7",
                    "Samsung Odyssey G7",
                    monitorCat.get().getId(),
                    "Samsung",
                    "Odyssey G7",
                    "SOG7C32",
                    "32-inch curved gaming monitor with QHD resolution and 240Hz refresh rate.",
                    25,
                    699.99,
                    true,
                    "2"
            ));
        });

        monitorCat.ifPresent(category -> {
            products.add(new Product(
                    "8",
                    "LG UltraFine 5K",
                    monitorCat.get().getId(),
                    "LG",
                    "UltraFine 5K",
                    "LGUF5K",
                    "27-inch 5K display with Thunderbolt 3 for Mac users, perfect for creative professionals.",
                    20,
                    1299.99,
                    true,
                    "2"
            ));
        });


        mouseCat.ifPresent(category1 -> {
            products.add(new Product(
                    "9",
                    "Razer DeathAdder V2",
                    mouseCat.get().getId(),
                    "Razer",
                    "DeathAdder V2",
                    "RDAV2",
                    "Ergonomic gaming mouse with 20,000 DPI optical sensor and Razer Chroma RGB.",
                    50,
                    59.99,
                    true,
                    "1"
            ));
        });


        mouseCat.ifPresent(category -> {

            products.add(new Product(
                    "10",
                    "Corsair Dark Core RGB Pro",
                    mouseCat.get().getId(),
                    "Corsair",
                    "Dark Core RGB Pro",
                    "CDCRGBPRO",
                    "Wireless gaming mouse with customizable buttons, 18,000 DPI, and RGB lighting.",
                    45,
                    89.99,
                    true,
                    "1"
            ));
        });

        productRepo.saveAll(products);

//━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━//

        //




    }

}
