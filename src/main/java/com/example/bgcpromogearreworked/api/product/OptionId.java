package com.example.bgcpromogearreworked.api.product;

import lombok.Data;

import java.io.Serializable;

@Data
public class OptionId implements Serializable {
    private Long productId;
    private String name;
}