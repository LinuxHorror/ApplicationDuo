package com.example.applicationduo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private UUID id;

    @NotBlank(message = "empty product title")
    private String productTitle;
    @NotBlank(message = "empty description")
    private String description;
    @NotNull(message = "empty price")
    private Float price;


}
