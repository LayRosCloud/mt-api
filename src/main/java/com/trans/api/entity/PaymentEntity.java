package com.trans.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name = "payments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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

    @ManyToOne
    @JoinColumn(name = "counterparty_id", nullable = false)
    private CounterpartyEntity counterparty;
}
