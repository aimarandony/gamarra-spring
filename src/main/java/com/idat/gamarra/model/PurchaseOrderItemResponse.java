package com.idat.gamarra.model;

import com.idat.gamarra.entity.Product;
import com.idat.gamarra.entity.PurchaseOrderStatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderItemResponse {

    private Product id;
    private Product descriptionFull;
    private Integer currentStock;
    private PurchaseOrderStatusType status;
}
