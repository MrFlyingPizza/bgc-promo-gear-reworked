package com.example.bgcpromogearreworked.api.product;

import lombok.Data;

import java.io.Serializable;

@Data
public class OptionValueId implements Serializable {
    private final Long productId;
    private final String name;
    private final String value;
}