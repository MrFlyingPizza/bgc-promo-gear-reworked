package com.example.bgcpromogearreworked.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "inventory_level")
@Entity
@Getter
@Setter
@IdClass(InventoryLevelId.class)
public class InventoryLevel {
    @Id
    @Column(name = "variant_id", nullable = false)
    private Long variantId;

    @Id
    @Column(name = "location_id", nullable = false)
    private Long locationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", updatable = false, insertable = false)
    private ProductVariant productVariant;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", updatable = false, insertable = false)
    private OfficeLocation officeLocation;

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