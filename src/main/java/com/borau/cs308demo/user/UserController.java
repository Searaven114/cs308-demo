package com.borau.cs308demo.user;

import com.borau.cs308demo.address.Address;
import com.borau.cs308demo.address.AddressDTO;
import com.borau.cs308demo.user.dto.ProfileDTO;
import com.borau.cs308demo.user.dto.UserLoginDTO;
import com.borau.cs308demo.user.dto.UserRegistrationDTO;
import com.borau.cs308demo.user.dto.UserResponseDTO;
import com.borau.cs308demo.user.exception.UserNotFoundException;
import com.borau.cs308demo.user.exception.UserRegistrationException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepo;




//    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO loginDTO) {
//        try {
//            String response = userService.login(loginDTO);
//            return ResponseEntity.ok().body(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }


    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDTO dto) {
        try {
            UserResponseDTO response = userService.registerUser(dto);
            return ResponseEntity.ok().body(response);
        } catch (UserRegistrationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    @PostMapping(value = "/user/update", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> updateUser(@RequestBody @Valid )


    @Secured({"ROLE_CUSTOMER", "ROLE_ADMIN", "ROLE_SALESMANAGER", "ROLE_PRODUCTMANAGER"})
    @GetMapping("/user/profile")
    public ResponseEntity<?> showProfile() {
        try {
            ProfileDTO profile = userService.getProfile();
            return ResponseEntity.ok().body(profile);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("USER NOT FOUND");
        }
    }

    //TODO BUNU ADMIN CONTROLLERINE TAŞI ! USERSERVICE ORADA CAGRILSIN
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserByAdmin(@PathVariable(required = true) String id) {


        User userToDelete = userRepo.findById(id).orElse(null);

        if (userToDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userService.deleteProfileAdmin(id);
        return ResponseEntity.ok("User deleted successfully");
    }


    @DeleteMapping("/user")
    public ResponseEntity<?> deleteOwnProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();

        User user = userRepo.findByEmail(mail);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userService.deleteProfile(user.getId());
        return ResponseEntity.ok("Your profile has been deleted successfully");
    }


    @PostMapping("/user/address")
    public ResponseEntity<?> addAddressToUser(@RequestBody @Valid AddressDTO dto) {

        // Retrieve the authenticated user from the security context
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication object is null or not authenticated
        if (auth == null || !auth.isAuthenticated()) {
            log.warn("Unauthorized access attempt: No authentication found or user not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        // Ensure that the principal is of type User
        if (!(auth.getPrincipal() instanceof User)) {
            log.warn("Unauthorized access attempt: Invalid principal type for user: {}", auth.getPrincipal());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user type");
        }

        // Cast the principal to the User class
        User user = (User) auth.getPrincipal();

        // Double-check if the user object is null
        if (user == null) {
            log.error("Unexpected error: Authenticated principal is null");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authenticated user not found");
        }

        // Proceed with the service logic and add the address
        log.info("User {} is adding a new address", user.getEmail());
        return userService.addAddress(user.getId(), dto);
    }
    // TODO "If you want more control over validation, you can create a custom @ExceptionHandler method for MethodArgumentNotValidException to catch validation errors globally."











}
