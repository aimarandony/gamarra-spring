package com.idat.gamarra.repository;

import com.idat.gamarra.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    List<SaleDetail> findAllBySaleId(Long saleId);
    boolean existsByProductId(Long productId);
}
