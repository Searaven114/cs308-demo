package com.borau.cs308demo.config.populator;



import com.borau.cs308demo.address.Address;
import com.borau.cs308demo.cart.Cart;
import com.borau.cs308demo.cart.CartRepository;
import com.borau.cs308demo.cart.CartService;
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
public class CartPopulator {

    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final DistributorRepository distributorRepo;
    private final CategoryRepository categoryRepo;
    private final CartService cartService;
    private final CartRepository cartRepo;


    @PostConstruct
    public void init() {

        log.info("[CartPopulator] Clearing Cart Collection.");
        cartRepo.deleteAll();

        User user1 = userRepo.findByEmail("customer1@example.com");
        User user2 = userRepo.findByEmail("customer2@example.com");
        User user3 = userRepo.findByEmail("customer3@example.com");
        User user4 = userRepo.findByEmail("fuat");

        // Populate carts for each user
        if (user1 != null) populateCartForUser(user1, List.of("1", "2"), List.of(2, 1));
        if (user2 != null) populateCartForUser(user2, List.of("3", "4"), List.of(7, 99));
        if (user3 != null) populateCartForUser(user3, List.of("5"), List.of(1));
        if (user4 != null) populateCartForUser(user4, List.of("2", "3", "4"), List.of(3, 2, 5));
    }


    // Function to populate the cart for a given user
    private void populateCartForUser(User user, List<String> productIds, List<Integer> quantities) {
        Cart cart = new Cart();  // Create a new cart object
        cart.setUserId(user.getId());  // Associate the cart with the user

        List<CartItem> cartItems = new ArrayList<>();

        // Loop through the product IDs and quantities and create cart items
        for (int i = 0; i < productIds.size(); i++) {
            String productId = productIds.get(i);
            int quantity = quantities.get(i);

            Optional<Product> productOpt = productRepo.findById(productId);
            if (productOpt.isPresent()) {
                CartItem cartItem = new CartItem(productOpt.get(), quantity);
                cartItems.add(cartItem);
            }
        }

        // Set cart items and save the cart
        cart.setCartItems(cartItems);
        cartRepo.save(cart);

        // Update the user's cartId to point to the newly created cart
        user.setCartId(cart.getId());
        userRepo.save(user);

        log.info("Populated cart {} for user {}", cart.getId(), user.getEmail());
    }


}
