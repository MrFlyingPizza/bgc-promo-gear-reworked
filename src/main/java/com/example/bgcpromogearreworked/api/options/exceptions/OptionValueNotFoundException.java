package com.example.bgcpromogearreworked.api.options.exceptions;

public class OptionValueNotFoundException extends RuntimeException {

    public OptionValueNotFoundException() {
        super("Option value not found.");
    }
}
