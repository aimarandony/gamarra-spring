package com.idat.gamarra.service;

import com.idat.gamarra.entity.Product;
import com.idat.gamarra.entity.SaleDetail;
import com.idat.gamarra.exception.InvalidDeleteException;
import com.idat.gamarra.exception.InvalidSaleException;
import com.idat.gamarra.exception.NotFoundException;
import com.idat.gamarra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private SaleDetailService saleDetailService;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public void existById(Long id) {
        if (!repository.existsById(id)) throw new NotFoundException(id);
    }

    @Override
    public List<Product> saveAll(List<Product> productList) {
        return repository.saveAll(productList);
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
    public void updateStock(List<SaleDetail> saleDetailList) {
        String msgInvalidStock = "El Producto con ID %s no cuenta con el stock necesario para realizar la venta. Stock Disponible: %d";
        saleDetailList.forEach(saleDetail -> {
            Product productFound = findById(saleDetail.getProduct().getId());
            if (productFound.getStock() < saleDetail.getQuantity() || productFound.getStock() == 0)
                throw new InvalidSaleException(String.format(msgInvalidStock, productFound.getId(), productFound.getStock()));

            productFound.setStock(productFound.getStock() - saleDetail.getQuantity());
            if (productFound.getStock() == 0) productFound.setActive(false);
            repository.save(productFound);
        });
    }

    @Override
    public void deleteById(Long id) {
        String msg = "El producto con ID %d se encuentra en uso y no puede ser eliminado.";
        if (!repository.existsById(id)) throw new NotFoundException(id);
        if (saleDetailService.existByProductId(id)) throw new InvalidDeleteException(String.format(msg, id));
        repository.deleteById(id);
    }
}
