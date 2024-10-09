package com.borau.cs308demo.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
