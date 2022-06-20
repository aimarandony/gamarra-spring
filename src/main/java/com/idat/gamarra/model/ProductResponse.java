package com.idat.gamarra.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {

    private Long id;
    private String description;
    private String size;
    private String color;
    private Double price;
    private Integer stock;
    private Boolean active;
}
