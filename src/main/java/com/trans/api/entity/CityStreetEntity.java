package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "cities_streets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityStreetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "index", nullable = false)
    private String index;

    @Column(name = "city_id", nullable = false, updatable = false, insertable = false)
    private Integer cityId;

    @Column(name = "street_id", nullable = false, updatable = false, insertable = false)
    private Integer streetId;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;

    @ManyToOne
    @JoinColumn(name = "street_id", nullable = false)
    private StreetEntity street;
}
