package com.borau.cs308demo.view;


import com.borau.cs308demo.user.User;
import com.borau.cs308demo.user.UserRepository;
import com.borau.cs308demo.user.UserService;
import com.borau.cs308demo.user.dto.UserRegistrationDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//mvc
@AllArgsConstructor
@Controller
public class PageController {

    private final UserRepository userRepo;
    private final UserService userService;

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "index";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        // Add UserRegistrationDTO to the model
        model.addAttribute("UserDTO", new UserRegistrationDTO());
        return "register";
    }

}
