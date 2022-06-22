package com.idat.gamarra.config;

import com.idat.gamarra.entity.Customer;
import com.idat.gamarra.entity.Product;
import com.idat.gamarra.service.CustomerService;
import com.idat.gamarra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PopulateConfig {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    //@PostConstruct
    public void databaseInit() {
        Product product1, product2, product3, product4, product5;
        Customer customer;

        product1 = new Product();
        product1.setDescription("Polo Manga Corta");
        product1.setSize("M");
        product1.setColor("VERDE");
        product1.setPrice(28.00);
        product1.setStock(24);
        productService.create(product1);

        product2 = new Product();
        product2.setDescription("Polo Manga Corta");
        product2.setSize("S");
        product2.setColor("ROJO");
        product2.setPrice(25.00);
        product2.setStock(24);
        productService.create(product2);

        product3 = new Product();
        product3.setDescription("Polo Manga Larga");
        product3.setSize("M");
        product3.setColor("AZUL");
        product3.setPrice(28.00);
        product3.setStock(24);
        productService.create(product3);

        product4 = new Product();
        product4.setDescription("Polo Manga Larga");
        product4.setSize("S");
        product4.setColor("NEGRO");
        product4.setPrice(25.00);
        product4.setStock(0);
        productService.create(product4);

        product5 = new Product();
        product5.setDescription("Polo Manga Corta");
        product5.setSize("L");
        product5.setColor("BLANCO");
        product5.setPrice(30.00);
        product5.setStock(12);
        productService.create(product5);

        customer = new Customer();
        customer.setName("Aimar");
        customer.setLastName("Berrocal");
        customer.setEmail("aimar@gmail.com");
        customer.setCellphone("978544971");
        customer.setPassword("123456");
        customerService.create(customer);
    }
}
