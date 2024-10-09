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


    @Secured({"ROLE_ADMIN"})
    @GetMapping("/show-all-users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok().body( userService.getUsers() );
    }


//    @DeleteMapping("/admin/users/{userId}")
//    public ResponseEntity<?> removeUser(@PathVariable String userId) {
//        // Logic to remove a user
//    }




    //listAllUsers


    //removeUser


    //deleteAllUsers


    //

}
