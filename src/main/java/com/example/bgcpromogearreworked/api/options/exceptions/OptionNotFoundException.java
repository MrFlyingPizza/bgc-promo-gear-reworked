package com.example.bgcpromogearreworked.api.options.exceptions;

public class OptionNotFoundException extends RuntimeException {

    public OptionNotFoundException() {
        super("Option not found.");
    }
}
