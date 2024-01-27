package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "stocks_materials")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockMaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stock_id", nullable = false, insertable = false, updatable = false)
    private Short stockId;

    @Column(name = "material_id", nullable = false, insertable = false, updatable = false)
    private Integer materialId;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private StockEntity stock;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private MaterialEntity material;

    @Column(name = "count", nullable = false)
    private Integer count;
}
