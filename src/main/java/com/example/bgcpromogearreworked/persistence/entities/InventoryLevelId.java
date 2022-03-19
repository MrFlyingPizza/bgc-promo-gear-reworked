package com.example.bgcpromogearreworked.persistence.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class InventoryLevelId implements Serializable {
    private Long locationId;
    private Long variantId;

}