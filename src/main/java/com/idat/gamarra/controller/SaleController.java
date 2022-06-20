package com.idat.gamarra.controller;

import com.idat.gamarra.model.FullSaleRequest;
import com.idat.gamarra.model.FullSaleResponse;
import com.idat.gamarra.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("sales")
@RestController
@CrossOrigin
public class SaleController {

    @Autowired
    private SaleService service;

    @PostMapping
    @Operation(summary = "Crear Venta Completa")
    public FullSaleResponse createFull(@Valid @RequestBody FullSaleRequest fullSaleRequest) {
        return service.createFull(fullSaleRequest);
    }
}
