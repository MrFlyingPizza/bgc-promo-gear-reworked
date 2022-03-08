package com.example.bgcpromogearreworked.api.options.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public class OptionValueId implements Serializable {
    private final String name;
    private final String value;
}