package com.idat.gamarra.service;

import com.idat.gamarra.entity.Sale;

import java.util.List;

public interface SaleService {

    List<Sale> findAll();
    Sale findById(Long id);
    Sale create(Sale sale);
    Sale update(Sale sale, Long id);
    void deleteById(Long id);
}
