package com.example.bgcpromogearreworked.api.inventory;

import com.example.bgcpromogearreworked.api.user.User;
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
    @Column(name = "variant_id")
    private Long variantId;

    @Id
    @Column(name = "location_id")
    private Long locationId;

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