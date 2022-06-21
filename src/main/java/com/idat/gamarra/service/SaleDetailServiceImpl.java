package com.idat.gamarra.service;

import com.idat.gamarra.entity.SaleDetail;
import com.idat.gamarra.repository.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleDetailServiceImpl implements SaleDetailService{

    @Autowired
    private SaleDetailRepository repository;

    @Override
    public List<SaleDetail> createAll(List<SaleDetail> saleDetailList) {
        return repository.saveAll(saleDetailList);
    }

    @Override
    public List<SaleDetail> finBySaleId(Long saleId) {
        return repository.findAllBySaleId(saleId);
    }

    @Override
    public boolean existByProductId(Long productId) {
        return repository.existsByProductId(productId);
    }
}
