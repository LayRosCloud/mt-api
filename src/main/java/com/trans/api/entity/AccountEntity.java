package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "number", nullable = false)
    private Long number;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private CounterpartyEntity buyer;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private CounterpartyEntity receiver;

    @ManyToOne
    @JoinColumn(name = "stock_material_id", nullable = false)
    private StockMaterialEntity stockMaterial;
}
