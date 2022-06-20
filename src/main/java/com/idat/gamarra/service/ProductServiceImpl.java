package com.idat.gamarra.service;

import com.idat.gamarra.entity.Product;
import com.idat.gamarra.exception.NotFoundException;
import com.idat.gamarra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Product create(Product product) {
        product.setActive(true);
        if (product.getStock() == 0) product.setActive(false);
        return repository.save(product);
    }

    @Override
    public Product update(Product product, Long id) {
        Product productFound = findById(id);
        product.setId(productFound.getId());
        product.setActive(productFound.getActive());
        if (product.getStock() == 0) product.setActive(false);
        return repository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException(id);
        repository.deleteById(id);
    }
}
