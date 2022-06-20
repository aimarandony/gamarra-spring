package com.idat.gamarra.service;

import com.idat.gamarra.entity.Customer;
import com.idat.gamarra.exception.NotFoundException;
import com.idat.gamarra.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
