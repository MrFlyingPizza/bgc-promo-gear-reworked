package ca.bgcengineering.promogearreworked.api.options.exceptions;

public class OptionValueNotFoundException extends RuntimeException {

    public OptionValueNotFoundException() {
        super("Option value not found.");
    }
}
