package com.example.applicationduo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Integer id;

    @NotBlank(message = "empty product title")
    private String productTitle;
    @NotBlank(message = "empty description")
    private String description;
    @NotNull(message = "empty price")
    private Float price;


}
