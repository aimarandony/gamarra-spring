package com.idat.gamarra.model;

import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ProductRequest {

    @NotNull(message = "La descripción no puede ser nulo.")
    @NotBlank(message = "La descripción no puede estar vacía.")
    private String description;
    @NotNull(message = "La talla no puede ser nulo.")
    @NotBlank(message = "La talla no puede estar vacía.")
    private String size;
    @NotNull(message = "El color no puede ser nulo.")
    @NotBlank(message = "El color no puede estar vacío.")
    private String color;
    @NotNull(message = "El precio no puede ser nulo.")
    @DecimalMin(value = "0.0", message = "El precio no puede ser menor a 0.")
    private Double price;
    @NotNull(message = "El stock no puede ser nulo.")
    @Min(value = 0, message = "El stock no puede ser menor a 0.")
    private Integer stock;
}
