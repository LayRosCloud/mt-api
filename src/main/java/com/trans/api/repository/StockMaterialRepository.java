package com.trans.api.repository;

import com.trans.api.entity.StockMaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockMaterialRepository extends JpaRepository<StockMaterialEntity, Integer> {
    List<StockMaterialEntity> findByStockId(Short stockId);
    Optional<StockMaterialEntity> findByStockIdAndMaterialId(Short stockId, Integer materialId);
}
