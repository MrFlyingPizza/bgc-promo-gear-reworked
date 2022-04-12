package ca.bgcengineering.promogearreworked.api.orders.secured.dto;

import ca.bgcengineering.promogearreworked.api.orders.constraints.ValidClientOrEventExtraInfo;
import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.officelocationexists.OfficeLocationExists;
import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.userexists.UserExists;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@ValidClientOrEventExtraInfo
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SecuredOrderCreate {

    @Getter
    public static class NestedOrderItem {
        private final Long variantId;
        private final Integer quantity;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public NestedOrderItem(Long variantId, Integer quantity) {
            this.variantId = variantId;
            this.quantity = quantity;
        }
    }

    @Getter
    public static class NestedExtraInfo {
        private final String recipientInfo;
        private final Instant requiredDate;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public NestedExtraInfo(String recipientInfo, Instant requiredDate) {
            this.recipientInfo = recipientInfo;
            this.requiredDate = requiredDate;
        }
    }

    @JsonIgnore
    @Setter
    private Long submitterId;

    @NotNull
    @UserExists
    private final Long recipientId;

    @UserExists
    private final Long fulfillerId;

    @Size(min = 1, max = 500)
    private final String submitterComments;

    @Size(min = 1, max = 500)
    private final String fulfillerComments;

    @NotNull
    @OfficeLocationExists
    private final Long locationId;

    @NotNull
    private final Order.Status status;

    @NotNull
    private final Order.Type type;

    private final NestedExtraInfo extraInfo;

    private final List<NestedOrderItem> items;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SecuredOrderCreate(Long recipientId,
                              Long fulfillerId,
                              String submitterComments,
                              String fulfillerComments,
                              Long locationId,
                              Order.Status status,
                              Order.Type type,
                              NestedExtraInfo extraInfo,
                              List<NestedOrderItem> items) {
        this.recipientId = recipientId;
        this.fulfillerId = fulfillerId;
        this.submitterComments = submitterComments;
        this.fulfillerComments = fulfillerComments;
        this.locationId = locationId;
        this.status = status;
        this.type = type;
        this.extraInfo = extraInfo;
        this.items = items;
    }
}
