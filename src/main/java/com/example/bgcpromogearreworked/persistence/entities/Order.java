package com.example.bgcpromogearreworked.persistence.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
public class Order {

    public enum Status {

        SUBMITTED("submitted"),
        PROCESSING("processing"),
        COMPLETED("completed"),
        WAIT_LIST("waitlisted"),
        REJECTED("cancelled");

        Status(String value) {
        }
    }

    public enum Type {
        REGULAR("regular"),
        CLIENT("client"),
        EVENT("event");

        Type(String value) {
        }
    }

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "submitter", nullable = false)
    private User submitter;

    @ManyToOne
    @JoinColumn(name = "fulfiller")
    private User fulfiller;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recipient", nullable = false)
    private User recipient;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "submitter_comments", nullable = false, length = 500)
    private String submitterComments = "";

    @Column(name = "fulfiller_comments", nullable = false, length = 500)
    private String fulfillerComments = "";

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private OfficeLocation location;

    @Column(name = "owed_credit")
    private BigDecimal owedCredit;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

    @Column(name = "completed_date")
    private Instant completedDate;

    @OneToOne(mappedBy = "order", cascade = {CascadeType.PERSIST})
    private OrderExtraInfo extraInfo;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Set<OrderItem> orderItems = new java.util.LinkedHashSet<>();

    @Transient
    @Setter(AccessLevel.PRIVATE)
    private BigDecimal totalCost;

    @PostLoad
    private void calculateTotalCost() {
        for (OrderItem orderItem : orderItems) {
            this.totalCost = this.totalCost.add(orderItem.getCost());
        }
    }

}