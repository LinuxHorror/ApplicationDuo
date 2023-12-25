package com.example.applicationduo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationDto {

    private UUID id;

    private String role;

    @NotBlank(message = "empty email")
    private String username;

    @NotBlank(message = "empty password")
    @Length(max = 15, message = "length of should be < 15")
    private String password;
}
