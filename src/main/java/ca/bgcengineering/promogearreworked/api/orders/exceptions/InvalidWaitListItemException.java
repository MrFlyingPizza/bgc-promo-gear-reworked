package ca.bgcengineering.promogearreworked.api.orders.exceptions;

import lombok.Getter;

public class InvalidWaitListItemException extends RuntimeException {

    @Getter
    private final Long variantId;

    public InvalidWaitListItemException(Long variantId) {
        super("Wait list item is invalid.");
        this.variantId = variantId;
    }
}
