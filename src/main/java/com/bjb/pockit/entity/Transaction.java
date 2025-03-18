package com.bjb.pockit.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userProfileId;

    private Long splitBillId;

    @Column(nullable = false)
    private Long transactionType;

    @Column(nullable = false)
    private Long pocketId;

    private String image;
    private String description;
    private String tag;
    private LocalDate transDate;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Integer status;

    private LocalDateTime deletedDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
}