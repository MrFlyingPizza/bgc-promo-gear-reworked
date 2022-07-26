package ca.bgcengineering.promogearreworked.api.transfers.exceptions;

import lombok.Getter;

@Getter
public class AbstractTransferQuantityException extends RuntimeException {

    private final int quantity;

    public AbstractTransferQuantityException(int quantity) {
        super("Invalid transfer quantity of %d.".formatted(quantity));
        this.quantity = quantity;
    }
}
