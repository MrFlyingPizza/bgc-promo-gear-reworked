package com.example.bgcpromogearreworked.api.products.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public class OptionValueId implements Serializable {
    private String name;
    private String value;
}