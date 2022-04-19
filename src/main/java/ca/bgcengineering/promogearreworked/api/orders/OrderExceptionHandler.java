package ca.bgcengineering.promogearreworked.api.orders;

import ca.bgcengineering.promogearreworked.api.orders.exceptions.InsufficientCreditException;
import ca.bgcengineering.promogearreworked.api.orders.exceptions.InvalidWaitListItemException;
import ca.bgcengineering.promogearreworked.api.orders.exceptions.NoCartItemException;
import ca.bgcengineering.promogearreworked.api.orders.exceptions.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(NoCartItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Map<?,?> handleNoCartItem(NoCartItemException exception) {
        return Map.of("message", "No cart items exist on user.");
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private Map<?,?> handleOrderNotFound(OrderNotFoundException exception) {
        return Map.of("message","Order could not be found.");
    }

    @ExceptionHandler(InsufficientCreditException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Map<?,?> handleInsufficientCredit(InsufficientCreditException exception) {
        return Map.of("message","Insufficient credit to complete this order.");
    }

    @ExceptionHandler(InvalidWaitListItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Map<?,?> handleInvalidWaitListItem(InvalidWaitListItemException exception) {
        return Map.of("message", "An item with insufficient quantity cannot be wait-listed.", "variantId", exception.getVariantId());
    }

}
