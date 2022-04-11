package com.example.bgcpromogearreworked.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "\"user\"", indexes = {
        @Index(name = "user_oid_uindex", columnList = "oid", unique = true)
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "oid", nullable = false)
    private UUID oid;

    @Column(name = "credit", nullable = false, precision = 10, scale = 2)
    private BigDecimal credit;

    @Column(name = "owed_credit", nullable = false, precision = 10, scale = 2)
    private BigDecimal owedCredit = BigDecimal.ZERO;

    @Column(name = "last_big_item_date")
    private Instant lastBigItemDate;

    @Column(name = "display_name", length = 256)
    private String displayName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private OfficeLocation location;
}