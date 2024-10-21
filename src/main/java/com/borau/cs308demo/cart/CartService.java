package com.borau.cs308demo.cart;

import com.borau.cs308demo.cartitem.CartItem;
import com.borau.cs308demo.product.Product;
import com.borau.cs308demo.product.ProductRepository;
import com.borau.cs308demo.product.ProductService;
import com.borau.cs308demo.product.exception.ProductNotFoundException;
import com.borau.cs308demo.user.User;
import com.borau.cs308demo.user.UserRepository;
import com.borau.cs308demo.user.UserService;
import com.borau.cs308demo.user.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
public class CartService {

    private final UserService userService;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final HttpSession session;
    private final CartRepository cartRepo;


    private void recalculateTotalPrice(Cart cart) {
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getBasePrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(totalPrice);
    }


    /*
    *  - Retreive Cart
    *  - Add product to cart
    *  - Remove product from cart
    *  - Clear the cart
    *  - Checkout? yada order -> checkoutta mesela addressi olması lazım tamamlanması icin, address yoksa kullanıcıya bildirim gitmeli adresin yok diye.
    *  - (ileri seviye) Check Stock Availability -> cart'a ekledigi urunlere 4 ay sonra gelip baktıgında illa stok durumu farklı olacaktır, belirli bir işlem/sürede tetiklenen bu fonksiyon,
    *       cartta stogu bitmis urun olunca carrtan otomatik olarak çıkartmalı o ürünü ve (We have removed x, y and z due to no stock available) gibi bir mesaj döndürmeli.
    *
    * */


    //Calışmıyor
    public List<CartItem> getAllCartItems(String userId){

        Cart cart = cartRepo.findByUserId(userId);

        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setTotalPrice(0.0);
        }

        return cart.getCartItems();
    }



    public void addItemToUserCart(String userId, String productId, int quantity) {

        Cart cart = cartRepo.findByUserId(userId);

        if (cart == null) {
            cart = new Cart();  // Create new cart if it doesn't exist
        }

        Optional<Product> productOpt = productRepo.findById(productId);
        if (productOpt.isPresent()) {

            Product product = productOpt.get();

            CartItem cartItem = new CartItem(product, quantity);

            List<CartItem> items = cart.getCartItems();

            items.add(cartItem);

            //List<CartItem> items =  cart.getCartItems().add(cartItem);  // Add item to cart

            recalculateTotalPrice(cart);

            cartRepo.save(cart);

        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }



}























//    // Merge the session cart with the user's cart
//    private void mergeCarts(Cart sessionCart, Cart userCart) {

//        for (CartItem sessionCartItem : sessionCart.getCartItems()) {
//            boolean productExists = false;
//
//            // Check if the session cart item already exists in the user's cart
//            for (CartItem userCartItem : userCart.getCartItems()) {
//                if (Objects.equals(userCartItem.getProduct().getId(), sessionCartItem.getProduct().getId())) {
//                    // Increase the quantity of the existing product in the user's cart
//                    userCartItem.setQuantity(userCartItem.getQuantity() + sessionCartItem.getQuantity());
//                    cartItemRepository.save(userCartItem);
//                    productExists = true;
//                    break;
//                }
//            }
//
//            // If the product doesn't exist in the user's cart, add it
//            if (!productExists) {
//                CartItem newItem = CartItem.builder()
//                        .product(sessionCartItem.getProduct())
//                        .quantity(sessionCartItem.getQuantity())
//                        .build();
//                userCart.getCartItems().add(newItem);
//                cartItemRepository.save(newItem);
//            }
//        }
//
//        // Save the updated user's cart
//        cartRepository.save(userCart);
//
//        // Clear the session cart
//        sessionCart.getCartItems().clear();
//        cartRepository.save(sessionCart);
//    }




