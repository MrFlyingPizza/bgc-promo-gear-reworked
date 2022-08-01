package ca.bgcengineering.promogearreworked.api.orders.secured.dto;

import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.userexists.UserExists;
import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
public class SecuredOrderUpdate {
    @Setter
    @JsonIgnore
    private Long Id;

    private final Order.Status status;

    private final Order.Type type;

    @Size(min = 1, max = 500)
    private final String fulfillerComments;

    @UserExists
    private final Long recipientId;

    @UserExists
    private final Long fulfillerId;

    @JsonCreator
    public SecuredOrderUpdate(@JsonProperty("status") Order.Status status,
                              @JsonProperty("type") Order.Type type,
                              @JsonProperty("fulfillerComments") String fulfillerComments,
                              @JsonProperty("recipientId") Long recipientId,
                              @JsonProperty("fulfillerId") Long fulfillerId) {
        this.status = status;
        this.type = type;
        this.fulfillerComments = fulfillerComments;
        this.recipientId = recipientId;
        this.fulfillerId = fulfillerId;
    }
}
