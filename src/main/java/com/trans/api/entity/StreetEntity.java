package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "streets")
@Data
public class StreetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
}
