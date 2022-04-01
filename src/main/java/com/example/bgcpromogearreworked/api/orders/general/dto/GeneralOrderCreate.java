package com.example.bgcpromogearreworked.api.orders.general.dto;

import com.example.bgcpromogearreworked.api.orders.constraints.ValidClientOrEventExtraInfo;
import com.example.bgcpromogearreworked.api.shared.validation.constraints.officelocationexists.OfficeLocationExists;
import com.example.bgcpromogearreworked.api.shared.validation.constraints.productvariantexists.ProductVariantExists;
import com.example.bgcpromogearreworked.persistence.entities.Order;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@ValidClientOrEventExtraInfo
public class GeneralOrderCreate {

    @Getter
    public static class NestedOrderExtraInfo {
        @Size(min = 1, max = 256)
        private final String recipientInfo;
        @Future
        private final Instant requiredDate;
        NestedOrderExtraInfo(@JsonProperty("recipientInfo") String recipientInfo,
                             @JsonProperty("requiredDate") Instant requiredDate) {
            this.recipientInfo = recipientInfo;
            this.requiredDate = requiredDate;
        }
    }

    @Getter
    public static class NestedOrderItem {
        @ProductVariantExists
        private final Long variantId;
        @Min(1)
        private final Integer quantity;

        private final BigDecimal price;
        @JsonCreator
        NestedOrderItem(@JsonProperty("variantId") Long variantId,
                        @JsonProperty("quantity") Integer quantity,
                        @JsonProperty("price") BigDecimal price) {
            this.variantId = variantId;
            this.quantity = quantity;
            this.price = price;
        }
    }

    @Setter
    @JsonIgnore
    private Long submitterId;

    @Setter
    @JsonIgnore
    private Long recipientId;

    @NotNull
    private final Order.Type type;

    @Size(max = 500)
    @NotNull
    private final String comments;

    @OfficeLocationExists
    @NotNull
    private final Long locationId;

    private final NestedOrderExtraInfo extraInfo;

    @JsonIgnore
    @Setter
    private List<NestedOrderItem> items;

    @JsonCreator
    public GeneralOrderCreate(@JsonProperty("comments") String comments,
                       @JsonProperty("locationId") Long locationId,
                       @JsonProperty("type") Order.Type type,
                       @JsonProperty("extraInfo") NestedOrderExtraInfo extraInfo) {
        this.comments = comments;
        this.locationId = locationId;
        this.type = type;
        this.extraInfo = extraInfo;
    }
}
