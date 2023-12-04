package com.example.applicationduo.utils;

import com.example.applicationduo.dto.ProductDto;

import java.util.Comparator;

public class ProductComparator implements Comparator<ProductDto> {
    @Override
    public int compare(ProductDto first, ProductDto second) {
        return (int) (first.getPrice() * first.getCount() - second.getCount() * second.getPrice());
    }
}
