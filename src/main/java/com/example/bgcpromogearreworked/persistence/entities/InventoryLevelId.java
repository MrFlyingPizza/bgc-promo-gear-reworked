package com.example.bgcpromogearreworked.persistence.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryLevelId implements Serializable {
    private Long locationId;
    private Long variantId;

}