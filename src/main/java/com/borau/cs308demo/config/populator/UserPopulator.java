package com.borau.cs308demo.config.populator;


import com.borau.cs308demo.address.Address;
import com.borau.cs308demo.cart.Cart;
import com.borau.cs308demo.cart.CartRepository;
import com.borau.cs308demo.cartitem.CartItem;
import com.borau.cs308demo.category.CategoryRepository;
import com.borau.cs308demo.distributor.DistributorRepository;
import com.borau.cs308demo.product.Product;
import com.borau.cs308demo.product.ProductRepository;
import com.borau.cs308demo.user.User;
import com.borau.cs308demo.user.UserRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Log4j2
@AllArgsConstructor
@Component
public class UserPopulator {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;
    private final ProductRepository productRepo;
    private final DistributorRepository distributorRepo;
    private final CategoryRepository categoryRepo;
    private final CartRepository cartRepo;

    private final Faker fake = new Faker();

    @PostConstruct
    public void init() {

    // Clean the collection to avoid duplicates on restart
    userRepo.deleteAll();
    log.info("[UserPopulator] Clearing User Collection.");


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

            // Rol dağıtım kısmı
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
                addresses.add(new Address(fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country()));

            } else if (number < 3) {
                addresses.add(new Address(fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country()));
                addresses.add(new Address("Home",fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country(), fake.lorem().characters(30)));

            } else {
                addresses.add(new Address(fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country()));
                addresses.add(new Address(fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country()));
                addresses.add(new Address("Home",fake.address().streetName(), fake.address().city(), fake.address().zipCode(), fake.address().country(), fake.lorem().characters(25)));
            }

            //Setting mock addresses
            user.setAddresses(addresses);



    //TODO BOZUK BU, MAPPINGDE PROBLEM VAR, YA EKSTRA FIELD VAR YA TYPE MISMATCH VAR YADA EKSIK FIELD VAR

//          // Create and assign a cart for "fuat"
//            if (user.getUsername().equals("fuat")) {
//
//                Cart cart = new Cart();
//
//                cart.setUser(user);
//
//                // Adding CartItems to the cart
//                List<CartItem> cartItems = new ArrayList<>();
//
//                Optional<Product> product1 = productRepo.findById("1");
//                Optional<Product> product2 = productRepo.findById("2");
//                Optional<Product> product3 = productRepo.findById("3");
//                Optional<Product> product4 = productRepo.findById("4");
//                Optional<Product> product5 = productRepo.findById("5");
//
//                // Declare CartItems outside the if block
//                CartItem cartItem1 = null;
//                CartItem cartItem2 = null;
//                CartItem cartItem3 = null;
//                CartItem cartItem4 = null;
//                CartItem cartItem5 = null;
//
//                if (product1.isPresent()) { cartItem1 = new CartItem(product1.get(), 2); }
//                if (product2.isPresent()) { cartItem2 = new CartItem(product2.get(), 1); }
//                if (product3.isPresent()) { cartItem3 = new CartItem(product3.get(), 7); }
//                if (product4.isPresent()) { cartItem4 = new CartItem(product4.get(), 99); }
//                if (product5.isPresent()) { cartItem5 = new CartItem(product5.get(), 1); }
//
//                if (cartItem1 != null) cartItems.add(cartItem1);
//                if (cartItem2 != null) cartItems.add(cartItem2);
//                if (cartItem3 != null) cartItems.add(cartItem3);
//                if (cartItem4 != null) cartItems.add(cartItem4);
//                if (cartItem5 != null) cartItems.add(cartItem5);
//
//                cart.setCartItems(cartItems);
//
//                user.setCart(cart);
//
//                userRepo.save(user);
//            }
        }


        // Save users with addresses
        userRepo.saveAll(users);

    }
}
