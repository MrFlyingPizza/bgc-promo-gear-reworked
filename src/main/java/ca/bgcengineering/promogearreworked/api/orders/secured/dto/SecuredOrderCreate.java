package ca.bgcengineering.promogearreworked.api.orders.secured.dto;

import ca.bgcengineering.promogearreworked.api.orders.constraints.ValidClientOrEventExtraInfo;
import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.officelocationexists.OfficeLocationExists;
import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.userexists.UserExists;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@ValidClientOrEventExtraInfo
@Getter
@RequiredArgsConstructor(onConstructor_ = @JsonCreator(mode = JsonCreator.Mode.PROPERTIES))
public class SecuredOrderCreate {

    @Getter
    @RequiredArgsConstructor(onConstructor_ = @JsonCreator(mode = JsonCreator.Mode.PROPERTIES))
    public static class NestedOrderItem {
        private final Long variantId;
        private final Integer quantity;
    }

    @Getter
    @RequiredArgsConstructor(onConstructor_ = @JsonCreator(mode = JsonCreator.Mode.PROPERTIES))
    public static class NestedExtraInfo {
        private final String recipientInfo;
        private final Instant requiredDate;
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


}
