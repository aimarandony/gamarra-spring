package com.idat.gamarra.service;

import com.idat.gamarra.entity.Product;
import com.idat.gamarra.entity.SaleDetail;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);
    void existById(Long id);
    List<Product> saveAll(List<Product> productList);
    Product create(Product product);
    Product update(Product product, Long id);
    void updateStock(List<SaleDetail> saleDetailList);
    void deleteById(Long id);
}
