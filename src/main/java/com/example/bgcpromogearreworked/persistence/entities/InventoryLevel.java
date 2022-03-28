package com.example.bgcpromogearreworked.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "inventory_level")
@Entity
@Getter
@Setter
@IdClass(InventoryLevelId.class)
public class InventoryLevel {

    @Id
    @Column(name = "location_id", nullable = false)
    private Long locationId;

    @Id
    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "variant_id", nullable = false, updatable = false, insertable = false)
    private ProductVariant variant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false, updatable = false, insertable = false)
    private OfficeLocation location;


    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity;

    @Column(name = "notify_threshold", nullable = false)
    private Integer notifyThreshold;

    @Column(name = "last_manually_modified_date")
    private Instant lastManuallyModifiedDate;

    @ManyToOne
    @JoinColumn(name = "last_manually_modified_by")
    private User lastManuallyModifiedBy;

}