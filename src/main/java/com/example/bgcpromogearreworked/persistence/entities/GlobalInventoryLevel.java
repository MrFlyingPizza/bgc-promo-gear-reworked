package com.example.bgcpromogearreworked.persistence.entities;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "global_inventory_level_view")
@Getter
public class GlobalInventoryLevel {
    @Id
    @Column(name = "variant_id", insertable = false, updatable = false)
    private Long variantId;

    @Column(name = "total_available_quantity")
    private Integer totalAvailableQuantity;

    @Column(name = "total_needed_quantity")
    private Integer totalNeededQuantity;

    @Transient
    private Integer apparentQuantity;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "globalInventoryLevel")
    private ProductVariant variant;

    @PostLoad
    private void calculateApparentQuantity() {
        this.apparentQuantity = totalAvailableQuantity - totalNeededQuantity - variant.getWaitListThreshold();
    }
}
