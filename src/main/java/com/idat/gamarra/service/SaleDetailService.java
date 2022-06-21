package com.idat.gamarra.service;

import com.idat.gamarra.entity.SaleDetail;

import java.util.List;

public interface SaleDetailService {

    List<SaleDetail> createAll(List<SaleDetail> saleDetailList);

    List<SaleDetail> finBySaleId(Long saleId);
}
