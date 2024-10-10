package com.borau.cs308demo.admin;

import com.borau.cs308demo.user.User;
import com.borau.cs308demo.user.UserRepository;
import com.borau.cs308demo.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Log4j2
@RequestMapping("/api/admin")
@RestController
public class AdminController {

    private final UserRepository userRepo;
    private final UserService userService;

//    The user should be able to browse and purchase the products through the website.
//    Additionally, the website should provide an admin interface for managerial tasks.



    @GetMapping("/show-all-users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok().body( userService.getUsers() );
    }


    /*@GetMapping("/users/{userId}")
    public User getUser(@PathVariable String userId) {
        // Code to get user details
        return userService.getUserById(userId);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // Code to create a new user
        return userService.createUser(user);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        // Code to update user details
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        // Code to delete a user
        userService.deleteUser(userId);
    }
*/
//    @PostMapping("/users/{userId}/reset-password")
//    public void resetPassword(@PathVariable String userId) {
//        // Code to reset password
//        userService.resetPassword(userId);
//    }



}
