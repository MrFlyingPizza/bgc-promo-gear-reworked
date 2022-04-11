package ca.bgcengineering.promogearreworked.api.options.exceptions;

public class OptionNotFoundException extends RuntimeException {

    public OptionNotFoundException() {
        super("Option not found.");
    }
}
