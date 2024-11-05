package com.borau.cs308demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String status;
    private String message;
    private Object data;
}
