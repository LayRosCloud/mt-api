package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "accounts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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
