package com.idat.gamarra.service;

import com.idat.gamarra.entity.Sale;
import com.idat.gamarra.exception.NotFoundException;
import com.idat.gamarra.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleRepository repository;

    @Override
    public List<Sale> findAll() {
        return repository.findAll();
    }

    @Override
    public Sale findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Sale create(Sale sale) {
        return null;
    }

    @Override
    public Sale update(Sale sale, Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException(id);
        repository.deleteById(id);
    }
}
