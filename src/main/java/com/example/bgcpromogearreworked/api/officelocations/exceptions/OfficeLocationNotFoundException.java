package com.example.bgcpromogearreworked.api.officelocations.exceptions;

public class OfficeLocationNotFoundException extends RuntimeException {

    public OfficeLocationNotFoundException() {
        super("Office location not found.");
    }
}
