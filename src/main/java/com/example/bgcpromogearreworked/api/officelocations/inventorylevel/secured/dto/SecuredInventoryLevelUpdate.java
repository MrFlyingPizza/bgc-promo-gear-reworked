package com.example.bgcpromogearreworked.api.officelocations.inventorylevel.secured.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
public class SecuredInventoryLevelUpdate {

    @JsonIgnore
    @Setter
    private Long locationId;

    @JsonIgnore
    @Setter
    private Long variantId;

    @NotNull
    @Min(0)
    private final Integer availableQuantity;

    @NotNull
    @Min(0)
    private final Integer reservedQuantity;

    @NotNull
    @Min(-1)
    private final Integer notifyThreshold;

    @JsonIgnore
    @Setter
    private Instant lastManuallyModifiedDate;

    @JsonIgnore
    @Setter
    private Long lastManuallyModifiedById;

    @JsonCreator
    SecuredInventoryLevelUpdate(@JsonProperty("availableQuantity") Integer availableQuantity,
                                @JsonProperty("reservedQuantity") Integer reservedQuantity,
                                @JsonProperty("notifyThreshold") Integer notifyThreshold) {
        this.availableQuantity = availableQuantity;
        this.reservedQuantity = reservedQuantity;
        this.notifyThreshold = notifyThreshold;
    }
}
