package com.borau.cs308demo.user.dto;

import com.borau.cs308demo.address.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private String phone; //05664526677

    @Min(1)
    @Max(150)
    private String age;

}
