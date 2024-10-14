package com.borau.cs308demo.cart;

import com.borau.cs308demo.cartitem.CartItem;
import com.borau.cs308demo.product.Product;
import com.borau.cs308demo.product.ProductService;
import com.borau.cs308demo.user.User;
import com.borau.cs308demo.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
@AllArgsConstructor
@Service
public class CartService {

    /*
    *  - Retreive Cart
    *  - Add product to cart
    *  - Remove product from cart
    *  - Clear the cart
    *  - Checkout? yada order
    *  - (ileri seviye) Check Stock Availability -> cart'a ekledigi urunlere 4 ay sonra gelip baktıgında illa stok durumu farklı olacaktır, belirli bir işlem/sürede tetiklenen bu fonksiyon,
    *       cartta stogu bitmis urun olunca carrtan otomatik olarak çıkartmalı o ürünü ve (We have removed x, y and z due to no stock available) gibi bir mesaj döndürmeli.
    *
    * */

    private final UserService userService;
    private final ProductService productService;
    private final CartRepository cartRepo;



//    public List<Product> getAllCartItems(){
//
//        // Retrieving the Principal from securith context to satisfy authorization
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) auth.getPrincipal();
//
//        if (user.getCart() == null) {
//            return ""
//        }
//
//        return user.getCart();
//
//
//        //return this.productRepository.findAll();
//
//
//
//        return null;
//    }

/*
    public String addItemById(String itemId){

    // If product is in the map just increment quantity by 1.
    // If product is not in the map with, add it with quantity 1


        return null;
    }


    public String removeProductFromCart(String cartItemId){
        try{
            this.cartItemRepository.deleteById(cartItemId);
        }catch (Exception e){
            throw new RuntimeException("Failed to remove product from cart");
        }
        return "Product successfully removed from cart";
    }


    public CartItem increaseDecreaseProductQuantity(String cartItemId, Integer value){
        CartItem cartItem = this.cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Could not find cart item"));

        if(value > 0 ){
            cartItem.setQuantity(cartItem.getQuantity() + value);
            return this.cartItemRepository.save(cartItem);
        }
        else{
            throw new RuntimeException("Product quantity should be above zero");
        }
    }



*/





    /*
    public CartResponse addProductInCart(
            String productId,
            HttpServletRequest request
    ){

        //User user = getUserByToken(request, jwtService, this.userRepository);

        Cart cart = this.cartRepository.findByUserId(user.getId());
        Product product = this.productRepository.findById(productId).orElse(null);

        LoggedUserResponse userResponse = LoggedUserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .build();

        //check if cart instance exists
        //if not create a new cart for the user
        if(cart == null){
            List<CartItem> cartItems = new ArrayList<>();
            //Create a new cart item and add it in a list
            CartItem savedCartItem = this.cartItemRepository.save(
                    CartItem.builder()
                            .product(product)
                            .quantity(1)
                            .build()
            );
            cartItems.add(savedCartItem);

            //create a new cart item and add them to the new cart
            //save the new cart in the database
            Cart savedCart = this.cartRepository.save(
                    Cart.builder()
                            .user(user)
                            .cartItems(cartItems)
                            .build()
            );

            return CartResponse.builder()
                    .id(savedCart.getId())
                    .user(userResponse)
                    .cartItems(savedCart.getCartItems())
                    .build();
        }
        else {
            //get all as a list cart items from cart
            //check if product already exists in one of the cart items
            if(cart.getCartItems() != null){
                for (CartItem cartItem : cart.getCartItems()) {
                    assert product != null;
                    if (Objects.equals(cartItem.getProduct().getId(), product.getId())) {
                        //if product exist then add a 1 to the quantity of the product item
                        cartItem.setQuantity(cartItem.getQuantity() + 1);

                        //save,update and return the cart item
                        this.cartRepository.save(cart);

                        return CartResponse.builder()
                                .id(cart.getId())
                                .user(userResponse)
                                .cartItems(cart.getCartItems())
                                .build();
                    }
                }
            }

            //if product does not exist
            //create a new cart item with product and quantity as 1
            CartItem savedCartItem = this.cartItemRepository.save(
                    CartItem.builder()
                            .product(product)
                            .quantity(1)
                            .build()
            );
            //add the cart item in the cart
            if(cart.getCartItems() != null){
                cart.getCartItems().add(savedCartItem);
            }else{
                List<CartItem> cartItemList = new ArrayList<>();
                cartItemList.add(savedCartItem);
                cart.setCartItems(cartItemList);
            }

            //save,update and return the cart item
            this.cartRepository.save(cart);
            return CartResponse.builder()
                    .id(cart.getId())
                    .user(userResponse)
                    .cartItems(cart.getCartItems())
                    .build();
        }

    }*/


    //━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━//

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




}
