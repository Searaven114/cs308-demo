package com.borau.cs308demo.user;

import com.borau.cs308demo.user.dto.ProfileDTO;
import com.borau.cs308demo.user.dto.UserRegistrationDTO;
import com.borau.cs308demo.user.dto.UserResponseDTO;
import com.borau.cs308demo.user.exception.UserRegistrationException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepo;

    @Valid
    @PostMapping(value = "/user", consumes = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO dto) {

        try {
            UserResponseDTO response = userService.registerUser(dto);
            return ResponseEntity.ok().body( response );

        } catch (UserRegistrationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( e.getMessage() );
        }
    }

    /*private String status;
    private String message;
    private Object data;*/

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/user/admin/show-all-users")
    public ResponseEntity<?> getUsers(){

        List<User> users = userRepo.findAll();
        return ResponseEntity.ok().body(users);
    }


    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_SALESMANAGER", "ROLE_PRODUCTMANAGER"})
    @GetMapping("/user/profile")
    public ResponseEntity<?> showProfile() {
        try {
            ProfileDTO profile = userService.getProfile();
            return ResponseEntity.ok().body(profile);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("USER NOT FOUND");
        }
    }


    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserByAdmin(@PathVariable(required = true) String id) {

        // Fetch the user to be deleted by ID
        User userToDelete = userRepo.findById(id).orElse(null);

        if (userToDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Perform the delete operation
        userService.deleteProfile(id);
        return ResponseEntity.ok("User deleted successfully");
    }


    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_SALESMANAGER", "ROLE_PRODUCTMANAGER"})
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










}
