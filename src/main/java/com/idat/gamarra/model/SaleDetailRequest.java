package com.idat.gamarra.model;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class SaleDetailRequest {

    @NotNull(message = "La cantidad no puede ser nulo.")
    @Min(value = 1, message = "La cantidad no puede ser menor a 1.")
    private Integer quantity;

    @NotNull(message = "La ID del Producto no puede ser nulo.")
    @NotBlank(message = "La ID del Producto no puede estar vacía.")
    private Long productId;
}
