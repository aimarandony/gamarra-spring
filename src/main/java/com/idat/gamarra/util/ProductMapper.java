package com.idat.gamarra.util;

import com.idat.gamarra.entity.Product;
import com.idat.gamarra.model.ProductRequest;
import com.idat.gamarra.model.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId()).description(product.getDescription())
                .size(product.getSize()).color(product.getColor())
                .price(product.getPrice()).stock(product.getStock())
                .active(product.getActive())
                .build();
    }

    public static List<ProductResponse> toProductResponseList(List<Product> products) {
        return products.stream().map(ProductMapper::toProductResponse).collect(Collectors.toList());
    }

    public static Product toProductEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setDescription(productRequest.getDescription());
        product.setSize(productRequest.getSize());
        product.setColor(productRequest.getColor());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        return product;
    }
}
