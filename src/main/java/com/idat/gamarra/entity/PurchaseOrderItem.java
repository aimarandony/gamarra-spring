package com.idat.gamarra.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class PurchaseOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer currentStock;
    private PurchaseOrderStatusType status;
    @ManyToOne
    private PurchaseOrder purchaseOrder;
    @ManyToOne
    private Product product;
}
