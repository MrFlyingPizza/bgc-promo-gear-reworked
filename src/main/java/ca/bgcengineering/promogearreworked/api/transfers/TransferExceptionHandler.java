package ca.bgcengineering.promogearreworked.api.transfers;

import ca.bgcengineering.promogearreworked.api.transfers.exceptions.InsufficientTransferQuantityException;
import ca.bgcengineering.promogearreworked.api.transfers.exceptions.InvalidTransferQuantityException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransferExceptionHandler {

    @ExceptionHandler(InvalidTransferQuantityException.class)
    private String handleInvalidTransferQuantity(InvalidTransferQuantityException exception) {
        return "A quantity of %d is invalid for transfer.".formatted(exception.getQuantity());
    }

    @ExceptionHandler(InsufficientTransferQuantityException.class)
    private String handleInsufficientTransferQuantity(InsufficientTransferQuantityException exception) {
        return "Cannot complete a transfer of %d because only %d is available at %s.".formatted(exception.getQuantity(),
                exception.getAvailableQuantity(), exception.getOriginName());
    }

}
