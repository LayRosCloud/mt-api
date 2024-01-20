package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "addresses")
@Data
public class AddressEntity {
    @Id
    private Long id;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(name = "corpus")
    private String corpus;

    @Column(name = "entrance")
    private String entrance;

    @Column(name = "apartment")
    private String apartment;

    @ManyToOne
    @JoinColumn(name = "city_street_id", nullable = false)
    private CityStreetEntity cityStreet;
}
