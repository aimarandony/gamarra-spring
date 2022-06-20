package com.idat.gamarra.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SaleDetailResponse {

    private Integer quantity;
    private Double unitPrice;
    private String productId;
    private String productDescriptionFull;
}
