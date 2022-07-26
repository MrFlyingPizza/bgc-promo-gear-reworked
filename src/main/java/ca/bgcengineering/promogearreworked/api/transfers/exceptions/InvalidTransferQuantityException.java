package ca.bgcengineering.promogearreworked.api.transfers.exceptions;

public class InvalidTransferQuantityException extends AbstractTransferQuantityException {
    public InvalidTransferQuantityException(int quantity) {
        super(quantity);
    }
}
