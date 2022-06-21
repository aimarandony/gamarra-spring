package com.idat.gamarra.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FullPurchaseOrderResponse {

    private LocalDateTime createdAt;
    private String adminFullName;
    private List<PurchaseOrderItemResponse> products;
}
