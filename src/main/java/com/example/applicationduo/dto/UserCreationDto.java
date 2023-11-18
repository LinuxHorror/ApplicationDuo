package com.example.applicationduo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationDto {
    private UUID id;
    private String username;
    private String email;
    private String password;
}
