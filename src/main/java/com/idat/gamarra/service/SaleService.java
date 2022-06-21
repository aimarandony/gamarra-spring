package com.idat.gamarra.service;

import com.idat.gamarra.entity.Sale;
import com.idat.gamarra.model.FullSaleRequest;
import com.idat.gamarra.model.FullSaleResponse;

import java.util.List;

public interface SaleService {

    List<Sale> findAll();
    Sale findById(Long id);
    Sale create(Sale sale);
    FullSaleResponse detailById(Long id);
    FullSaleResponse createFull(FullSaleRequest fullSaleRequest);
    Sale update(Sale sale, Long id);
    void deleteById(Long id);
    void cancelById(Long id);
}
