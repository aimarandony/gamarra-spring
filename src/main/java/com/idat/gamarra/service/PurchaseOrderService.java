package com.idat.gamarra.service;

import com.idat.gamarra.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {

    List<PurchaseOrder> findAll();
    PurchaseOrder findById(Long id);
    PurchaseOrder generate();
}
