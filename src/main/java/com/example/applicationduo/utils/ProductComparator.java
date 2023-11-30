package com.example.applicationduo.utils;

import com.example.applicationduo.entity.ProductEntity;

import java.util.Comparator;

public class ProductComparator implements Comparator<ProductEntity> {
    @Override
    public int compare(ProductEntity first, ProductEntity second) {
        return (int) (first.getPrice() * first.getCount() - second.getCount() * second.getPrice());
    }
}
