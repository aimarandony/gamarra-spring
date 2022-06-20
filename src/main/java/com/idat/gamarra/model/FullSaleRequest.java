package com.idat.gamarra.model;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class FullSaleRequest {

    @NotNull(message = "La dirección no puede ser nulo.")
    @NotBlank(message = "La dirección no puede estar vacía.")
    private String deliveryAddress;

    @NotNull(message = "La ID del Cliente no puede ser nulo.")
    private Long customerId;

    @NotNull(message = "La Carrito de Compra no puede ser nulo.")
    private List<SaleDetailRequest> carItems;
}
