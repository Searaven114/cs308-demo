package com.borau.cs308demo.user;


import com.borau.cs308demo.address.Address;
import com.borau.cs308demo.address.AddressDTO;
import com.borau.cs308demo.user.dto.ProfileDTO;
import com.borau.cs308demo.user.dto.UserLoginDTO;
import com.borau.cs308demo.user.dto.UserRegistrationDTO;
import com.borau.cs308demo.user.dto.UserResponseDTO;
import com.borau.cs308demo.user.exception.UserNotFoundException;
import com.borau.cs308demo.user.exception.UserRegistrationException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
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
    //private final AuthenticationManager authenticationManager;


//TODO: Add proper validation utilizing UserRegistrationException (derive more exceptions from this base exception class like invalidpasswordexcetion etc)


//    public String login(UserLoginDTO loginDTO) throws Exception {
//        // Authenticate the user credentials with Spring Security
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
//            );
//
//            // Set the authentication in the SecurityContext to mark the user as logged in
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            return "Login successful!";
//        } catch (Exception e) {
//            throw new Exception("Invalid username or password");
//        }
//    }

    @Secured({"ROLE_ADMIN"})
    public List<User> getUsers(){
        return userRepo.findAll();
    }


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

        //ilk kayıt ipsi yasa geregi tutmakla yukumlu cogu firma afaik
        String ip = "139.108.14.66";
        newUser.setRegisterIp(ip);

        LocalDateTime temp = LocalDateTime.now();
        newUser.setRegisterDate(temp.toString());

        userRepo.save(newUser);

        log.info("[UserService] User has been saved: " + newUser.toString());

        return new UserResponseDTO("Success", "User created",null);
    }



    public ProfileDTO getProfile() {
        // Get the currently authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        if (user == null) {
            throw new RuntimeException("User not found");  // custom exception ile değiştirilmeli..
        }

        return ProfileDTO.builder()
                .age(user.getAge())
                .name(user.getName())
                .email(user.getEmail())
                .surname(user.getSurname())
                .phone(user.getPhone())
                .registerDate(user.getRegisterDate())
                .build();
    }

    

    @Secured({"ROLE_ADMIN"})
    public void deleteProfile(String id) {

        if (id != null) {
            userRepo.deleteById(id);
        } else {
            throw new RuntimeException("User not found");
        }
    }


    public ResponseEntity<?> addAddress(String id, AddressDTO dto) {

        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("[UserService] User not found"));

        Address address = new Address();

        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setZipCode(dto.getZipCode());
        address.setCountry(dto.getCountry());

        user.getAddresses().add(address);

        userRepo.save(user);
        return ResponseEntity.ok("Address added successfully");
    }



}
