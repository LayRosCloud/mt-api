package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "materials")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "width", nullable = false)
    private Double width;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "depth", nullable = false)
    private Double depth;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private UnitEntity unit;
}
