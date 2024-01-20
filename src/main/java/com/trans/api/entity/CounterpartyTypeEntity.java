package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "counterparty_types")
@Data
public class CounterpartyTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "name")
    private String name;
}
