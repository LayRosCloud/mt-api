package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "counterparties")
@Data
public class CounterpartyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "inn")
    private String INN;

    @Column(name = "kpp")
    private String KPP;

    @Column(name = "correspondent_account")
    private String correspondentAccount;

    @Column(name = "bik")
    private String BIK;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private CounterpartyTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;
}
