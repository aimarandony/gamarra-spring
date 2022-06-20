package com.idat.gamarra.controller;

import com.idat.gamarra.entity.Product;
import com.idat.gamarra.service.ProductService;
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
    public List<Product> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public Product findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public Product create(@Valid @RequestBody Product product){
        return service.create(product);
    }

    @PutMapping("{id}")
    public Product update(@Valid @RequestBody Product product, @PathVariable Long id) {
        return service.update(product, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Map<String, Object> data = new HashMap<>();
        service.deleteById(id);
        data.put("message", "Product deleted successfully");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
