package com.idat.gamarra.util;

import com.idat.gamarra.entity.Customer;
import com.idat.gamarra.entity.Sale;
import com.idat.gamarra.entity.SaleDetail;
import com.idat.gamarra.model.FullSaleResponse;
import com.idat.gamarra.model.SaleDetailRequest;
import com.idat.gamarra.model.SaleDetailResponse;
import com.idat.gamarra.model.SaleResponse;

import java.util.List;
import java.util.stream.Collectors;


public class SaleMapper {

    public static SaleResponse toSaleResponse(Sale sale) {
        return SaleResponse.builder()
                .deliveryAddress(sale.getDeliveryAddress())
                .status(sale.getStatus()).createdAt(sale.getCreatedAt())
                .customerId(sale.getCustomer().getId().toString())
                .build();
    }

    public static SaleDetailResponse toSaleDetailResponse(SaleDetail saleDetail) {
        String productDescriptionFull = saleDetail.getProduct().getDescription().concat(" - ")
                .concat(saleDetail.getProduct().getSize()).concat(" - ")
                .concat(saleDetail.getProduct().getColor());

        return SaleDetailResponse.builder()
                .quantity(saleDetail.getQuantity())
                .unitPrice(saleDetail.getUnitPrice())
                .productId(saleDetail.getProduct().getId().toString())
                .productDescriptionFull(productDescriptionFull)
                .build();
    }

    public static List<SaleDetailResponse> toSaleDetailResponseList(List<SaleDetail> saleDetails) {
        return saleDetails.stream().map(SaleMapper::toSaleDetailResponse).collect(Collectors.toList());
    }

    public static FullSaleResponse toFullSaleResponse(Sale sale, List<SaleDetail> saleDetails, Customer customer, Double totalPrice) {
        FullSaleResponse fullSaleResponse = new FullSaleResponse();
        fullSaleResponse.setDeliveryAddress(sale.getDeliveryAddress());
        fullSaleResponse.setSaleCreatedAt(sale.getCreatedAt());
        fullSaleResponse.setSaleStatus(sale.getStatus());
        fullSaleResponse.setCustomerFullName(customer.getName().concat(" ").concat(customer.getLastName()));
        fullSaleResponse.setCustomerEmail(customer.getEmail());
        fullSaleResponse.setCustomerCellphone(customer.getCellphone());
        fullSaleResponse.setCarItems(toSaleDetailResponseList(saleDetails));
        fullSaleResponse.setTotalPrice(totalPrice);
        return fullSaleResponse;
    }
}
