package com.example.bgcpromogearreworked.api.inventorylevels.inventorylevel.secured.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.time.Instant;

@Getter
public class SecuredInventoryLevelPartialUpdate {

    @JsonIgnore
    @Setter
    private Long locationId;

    @JsonIgnore
    @Setter
    private Long variantId;

    @Min(0)
    private final Integer availableQuantity;

    @Min(0)
    private final Integer reservedQuantity;

    @Min(-1)
    private final Integer notifyThreshold;

    @JsonIgnore
    @Setter
    private Instant lastManuallyModifiedDate;

    @JsonIgnore
    @Setter
    private Long lastManuallyModifiedById;

    @JsonCreator
    SecuredInventoryLevelPartialUpdate(@JsonProperty("availableQuantity") Integer availableQuantity,
                                       @JsonProperty("reservedQuantity") Integer reservedQuantity,
                                       @JsonProperty("notifyThreshold") Integer notifyThreshold) {
        this.availableQuantity = availableQuantity;
        this.reservedQuantity = reservedQuantity;
        this.notifyThreshold = notifyThreshold;
    }

}
