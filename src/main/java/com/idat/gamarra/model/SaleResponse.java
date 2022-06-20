package com.idat.gamarra.model;

import com.idat.gamarra.entity.SaleStatusType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SaleResponse {

    private Long id;
    private String deliveryAddress;
    private LocalDateTime createdAt;
    private SaleStatusType status;
    private String customerId;
}
