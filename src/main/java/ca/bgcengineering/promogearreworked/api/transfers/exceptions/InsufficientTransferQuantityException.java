package ca.bgcengineering.promogearreworked.api.transfers.exceptions;

import lombok.Getter;

@Getter
public class InsufficientTransferQuantityException extends AbstractTransferQuantityException {

    private final int availableQuantity;
    private final String originName;

    public InsufficientTransferQuantityException(int quantity, int availableQuantity, String originName) {
        super(quantity);
        this.availableQuantity = availableQuantity;
        this.originName = originName;
    }
}
