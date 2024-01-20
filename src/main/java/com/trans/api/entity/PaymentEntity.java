package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "payments")
@Data
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number" , nullable = false)
    private String number;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "sum", nullable = false)
    private Double sum;
}
