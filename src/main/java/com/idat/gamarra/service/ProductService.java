package com.idat.gamarra.service;

import com.idat.gamarra.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);
    Product create(Product product);
    Product update(Product product, Long id);
    void deleteById(Long id);
}
