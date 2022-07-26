package ca.bgcengineering.promogearreworked.api.transfers.exceptions;

public class TransferNotFoundException extends RuntimeException {
    public TransferNotFoundException() {
        super("Transfer not found.");
    }
}
