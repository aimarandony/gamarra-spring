package com.idat.gamarra.controller;

import com.idat.gamarra.model.ProductRequest;
import com.idat.gamarra.model.ProductResponse;
import com.idat.gamarra.service.ProductService;
import com.idat.gamarra.util.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("products")
@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @Operation(summary = "Listar Productos")
    public List<ProductResponse> findAll(){
        return ProductMapper.toProductResponseList(service.findAll());
    }

    @GetMapping("{id}")
    @Operation(summary = "Obtener Producto por ID")
    public ProductResponse findById(@PathVariable Long id){
        return ProductMapper.toProductResponse(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear Producto")
    public ProductResponse create(@Valid @RequestBody ProductRequest productRequest){
        return ProductMapper.toProductResponse(service.create(ProductMapper.toProductEntity(productRequest)));
    }

    @PutMapping("{id}")
    @Operation(summary = "Actualizar Producto")
    public ProductResponse update(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long id) {
        return ProductMapper.toProductResponse(service.update(ProductMapper.toProductEntity(productRequest), id));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar Producto por ID")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();
        service.deleteById(id);
        data.put("message", String.format("Producto con ID %d eliminado correctamente.", id));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
