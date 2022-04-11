package com.example.bgcpromogearreworked.api.orders;

import com.example.bgcpromogearreworked.api.orders.exceptions.NoCartItemException;
import com.example.bgcpromogearreworked.api.orders.exceptions.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler(NoCartItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String handleNoCartItem(NoCartItemException exception) {
        return "No cart items exist on user.";
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleOrderNotFound(OrderNotFoundException exception) {
        return "Order could not be found.";
    }

}
