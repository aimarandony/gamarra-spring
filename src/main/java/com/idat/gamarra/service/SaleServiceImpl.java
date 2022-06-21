package com.idat.gamarra.service;

import com.idat.gamarra.entity.*;
import com.idat.gamarra.exception.NotFoundException;
import com.idat.gamarra.model.FullSaleRequest;
import com.idat.gamarra.model.FullSaleResponse;
import com.idat.gamarra.repository.SaleRepository;
import com.idat.gamarra.util.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SaleDetailService saleDetailService;

    @Override
    public List<Sale> findAll() {
        return repository.findAll();
    }

    @Override
    public Sale findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Sale create(Sale sale) {
        sale.setCreatedAt(LocalDateTime.now());
        sale.setStatus(SaleStatusType.CREATED);
        return repository.save(sale);
    }

    @Override
    public FullSaleResponse detailById(Long id) {
        Sale sale = findById(id);
        List<SaleDetail> saleDetailList = saleDetailService.finBySaleId(sale.getId());
        Customer customer = customerService.findById(sale.getCustomer().getId());
        double totalPrice = calcTotalPrice(saleDetailList);
        return SaleMapper.toFullSaleResponse(sale, saleDetailList, customer, totalPrice);
    }

    @Override
    @Transactional
    public FullSaleResponse createFull(FullSaleRequest fullSaleRequest) {

        // Find Customer by ID
        Customer customerFound = customerService.findById(fullSaleRequest.getCustomerId());

        // Get Car Items
        List<SaleDetail> carItems = fullSaleRequest.getCarItems().stream().map(saleDetailRequest -> {
            SaleDetail saleDetail = new SaleDetail();
            Product productFound = productService.findById(saleDetailRequest.getProductId());
            saleDetail.setProduct(productFound);
            saleDetail.setQuantity(saleDetailRequest.getQuantity());
            saleDetail.setUnitPrice(productFound.getPrice());
            return saleDetail;
        }).collect(Collectors.toList());

        // Create Sale
        Sale sale = new Sale();
        sale.setCustomer(customerFound);
        sale.setDeliveryAddress(fullSaleRequest.getDeliveryAddress());
        Sale saleCreated = create(sale);

        // Update Stock Products
        productService.updateStock(carItems);

        // Create Sale Detail
        List<SaleDetail> saleDetailList = new ArrayList<>();
        carItems.forEach(carItem -> {
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setProduct(productService.findById(carItem.getProduct().getId()));
            saleDetail.setQuantity(carItem.getQuantity());
            saleDetail.setUnitPrice(carItem.getUnitPrice());
            saleDetail.setSale(findById(saleCreated.getId()));
            saleDetailList.add(saleDetail);
        });
        List<SaleDetail> saleDetailListCreated = saleDetailService.createAll(saleDetailList);

        double totalPrice = calcTotalPrice(saleDetailList);

        return SaleMapper.toFullSaleResponse(saleCreated, saleDetailListCreated, customerFound, totalPrice);
    }

    @Override
    public Sale update(Sale sale, Long id) {
        Sale saleFound = findById(id);
        sale.setId(saleFound.getId());
        return repository.save(sale);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException(id);
        repository.deleteById(id);
    }

    @Override
    public void cancelById(Long id) {
        Sale sale = findById(id);

        List<SaleDetail> saleDetails = saleDetailService.finBySaleId(id);
        List<Product> productList = saleDetails.stream().peek(saleDetail -> {
            Product productUpd = saleDetail.getProduct();
            productUpd.setStock(productUpd.getStock() + saleDetail.getQuantity());
        }).map(SaleDetail::getProduct).collect(Collectors.toList());
        productService.saveAll(productList);

        sale.setStatus(SaleStatusType.CANCELED);
        update(sale, id);
    }

    private double calcTotalPrice(List<SaleDetail> saleDetailList) {
        return saleDetailList.stream().mapToDouble(saleDetail -> saleDetail.getQuantity() * saleDetail.getUnitPrice()).sum();
    }
}
