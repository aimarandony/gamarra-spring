package com.idat.gamarra.service;

import com.idat.gamarra.entity.Product;
import com.idat.gamarra.entity.PurchaseOrder;
import com.idat.gamarra.exception.NotFoundException;
import com.idat.gamarra.model.FullPurchaseOrderResponse;
import com.idat.gamarra.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    @Autowired
    private PurchaseOrderRepository repository;

    @Autowired
    private ProductService productService;

    @Override
    public List<PurchaseOrder> findAll() {
        return repository.findAll();
    }

    @Override
    public PurchaseOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public FullPurchaseOrderResponse generate(Integer stockMax) {
        //Obtener los productos con stock
        List<Product> productList = productService.findAll();

        return null;
    }
}
