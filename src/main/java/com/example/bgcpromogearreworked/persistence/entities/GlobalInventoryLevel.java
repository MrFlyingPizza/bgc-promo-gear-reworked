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

    @OneToOne
    @JoinColumn(name = "variant_id")
    private ProductVariant variant;

    @PostLoad
    @PostUpdate
    @PostPersist
    private void calculateApparentQuantity() {
        System.out.println(variant.getWaitListThreshold());
        this.apparentQuantity = totalAvailableQuantity - totalNeededQuantity - variant.getWaitListThreshold();
    }
}
