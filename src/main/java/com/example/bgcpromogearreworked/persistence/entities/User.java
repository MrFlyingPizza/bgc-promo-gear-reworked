package com.example.bgcpromogearreworked.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Table(name = "\"user\"")
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "oid", nullable = false)
    private UUID oid;

    @Column(name = "display_name", length = 256)
    private String displayName;

    @Column(name = "credit", nullable = false, precision = 131089)
    private BigDecimal credit;

    @Column(name = "last_big_item_date")
    private Instant lastBigItemDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private OfficeLocation office;

}