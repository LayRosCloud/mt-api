package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stocks_materials")
@Data
public class StockMaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private StockEntity stock;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private MaterialEntity material;
}
