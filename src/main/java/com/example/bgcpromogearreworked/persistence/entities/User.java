package com.example.bgcpromogearreworked.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Table(name = "user")
@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "oid", nullable = false)
    private UUID oid;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, length = 256)
    private String email;

    @Column(name = "credit", nullable = false, precision = 131089)
    private BigDecimal credit;

    @Column(name = "last_big_item")
    private Instant lastBigItem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "office_id", nullable = false)
    private OfficeLocation office;
}