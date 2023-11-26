package com.example.applicationduo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;


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

    @NotNull(message = "empty count of product")
    @Min(value = 0, message = "count should be positive")
    private Integer count;

    private byte[] imageToShow;

    @NotNull(message = "empty price")
    @Min(value = 0, message = "negative price")
    private Float price;

    public String getImageFromBytes(){
        return Base64.getEncoder().encodeToString(imageToShow);
    }

}
