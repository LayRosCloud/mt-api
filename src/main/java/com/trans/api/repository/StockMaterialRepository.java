package com.trans.api.repository;

import com.trans.api.entity.StockMaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockMaterialRepository extends JpaRepository<StockMaterialEntity, Integer> {
}
