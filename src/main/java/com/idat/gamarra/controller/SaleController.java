package com.idat.gamarra.controller;

import com.idat.gamarra.entity.Sale;
import com.idat.gamarra.model.FullSaleRequest;
import com.idat.gamarra.model.FullSaleResponse;
import com.idat.gamarra.model.SaleResponse;
import com.idat.gamarra.service.SaleService;
import com.idat.gamarra.util.SaleMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("sales")
@RestController
@CrossOrigin
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping
    @Operation(summary = "Listar Ventas")
    public List<SaleResponse> findAll(){
        return SaleMapper.toSaleResponseList(service.findAll());
    }

    @GetMapping("{id}")
    @Operation(summary = "Obtener Venta por ID")
    public SaleResponse findById(@PathVariable Long id) {
        return SaleMapper.toSaleResponse(service.findById(id));
    }

    @GetMapping("detail/{id}")
    @Operation(summary = "Detalle de Venta por ID")
    public FullSaleResponse detailById(@PathVariable Long id) {
        return service.detailById(id);
    }

    @PostMapping
    @Operation(summary = "Crear Venta Completa")
    public FullSaleResponse createFull(@Valid @RequestBody FullSaleRequest fullSaleRequest) {
        return service.createFull(fullSaleRequest);
    }

    @DeleteMapping("cancel/{id}")
    @Operation(summary = "Cancelar Venta por ID")
    public ResponseEntity<Object> cancelById(@PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();
        service.cancelById(id);
        data.put("message", String.format("La venta con ID %d ha sido cancelada.", id));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
