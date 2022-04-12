package ca.bgcengineering.promogearreworked.api.options;

import ca.bgcengineering.promogearreworked.api.options.exceptions.OptionNotFoundException;
import ca.bgcengineering.promogearreworked.api.options.exceptions.OptionValueNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OptionExceptionHandler {

    @ExceptionHandler(OptionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleOptionNotFound(OptionNotFoundException exception) {
        return "Option not found.";
    }

    @ExceptionHandler(OptionValueNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private String handleOptionValueNotFound(OptionValueNotFoundException exception) {
        return "Option value not found.";
    }

}
