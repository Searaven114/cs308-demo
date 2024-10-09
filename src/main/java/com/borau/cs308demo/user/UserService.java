package com.borau.cs308demo.user;


import com.borau.cs308demo.user.dto.ProfileDTO;
import com.borau.cs308demo.user.dto.UserRegistrationDTO;
import com.borau.cs308demo.user.dto.UserResponseDTO;
import com.borau.cs308demo.user.exception.UserRegistrationException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.*;

@Log4j2
@AllArgsConstructor
@Service
public class UserService{

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;


//TODO: Add proper validation utilizing UserRegistrationException (derive more exceptions from this base exception class like invalidpasswordexcetion etc)

    public UserResponseDTO registerUser(UserRegistrationDTO dto) throws UserRegistrationException {

        User check = userRepo.findByEmail( dto.getEmail() );

        //geliştirilebilir validation, frontend HTML form attributeleri 1. duvar, burada javax persistence bean validation annotationları
        // 2. duvar olmalı, annotation kullanılmazsa olabilir nullcheckli bu versiyon.

        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new UserRegistrationException("Email cannot be null or empty!");
        }

        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            throw new UserRegistrationException("Password cannot be null or empty!");
        }

        if (check != null){
            throw new UserRegistrationException("User already exists!");
        }

    //------------------ If and only if the received DTO passes all validation process we proceed from here: ------------------If
        User newUser = new User();

        newUser.setEmail(dto.getEmail());

        log.info("(DEBUG)(UserService.java) Received password : \"" + dto.getPassword() + "\" from user : " + dto.getEmail());
        newUser.setPassword( encoder.encode( dto.getPassword() ) );

        newUser.setName( dto.getName() );

        newUser.setSurname( dto.getSurname() );

        newUser.setPhone( dto.getPhone() );

        newUser.setAge( dto.getAge() );

        newUser.setAddresses( null );

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_CUSTOMER"); //ROLE_CUSTOMER, ROLE_ADMIN, ROLE_SALESMANAGER, ROLE_PRODUCTMANAGER
        newUser.setRoles(roles);

        //For legal reasons we will capture first register IP of the account owner, its to-be-implemented feature.
        String ip = "139.108.14.66";
        newUser.setRegisterIp(ip);

        LocalDateTime temp = LocalDateTime.now();
        newUser.setRegisterDate(temp.toString());

        userRepo.save(newUser);

        log.info("(DEBUG)(UserService.java) USER HAS BEEN SAVED : " + newUser.toString());

        return new UserResponseDTO("Success", "User created",null);
    }


    // New method for showing user profile
    public ProfileDTO getProfile() {
        // Get the currently authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");  // You could replace this with a custom exception
        }

        // Build the profile DTO
        return ProfileDTO.builder()
                .age(user.getAge())
                .name(user.getName())
                .email(user.getEmail())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .registerDate(user.getRegisterDate())
                .build();
    }

    public void deleteProfile(String id) {

        if (id != null) {
            userRepo.deleteById(id);
        } else {
            throw new RuntimeException("User not found");
        }
    }


}
