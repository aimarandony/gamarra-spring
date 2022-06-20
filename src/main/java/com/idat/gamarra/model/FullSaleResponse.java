package com.idat.gamarra.model;

import com.idat.gamarra.entity.SaleStatusType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FullSaleResponse {

    private String deliveryAddress;
    private LocalDateTime saleCreatedAt;
    private SaleStatusType saleStatus;
    private String customerFullName;
    private String customerEmail;
    private String customerCellphone;
    private List<SaleDetailResponse> carItems;
    private Double totalPrice;
}
