package com.idat.gamarra.service;

import com.idat.gamarra.entity.PurchaseOrder;
import com.idat.gamarra.model.FullPurchaseOrderResponse;

import java.util.List;

public interface PurchaseOrderService {

    List<PurchaseOrder> findAll();
    PurchaseOrder findById(Long id);
    FullPurchaseOrderResponse generate(Integer stockMax);
}
