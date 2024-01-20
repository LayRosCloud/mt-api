package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "supplies")
@Data
public class SupplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "sum", nullable = false)
    private Double sum;

    @ManyToOne
    @JoinColumn(name = "counterparty_id", nullable = false)
    private CounterpartyEntity counterparty;

    @ManyToOne
    @JoinColumn(name = "stock_material_id", nullable = false)
    private StockMaterialEntity stockMaterial;
}
