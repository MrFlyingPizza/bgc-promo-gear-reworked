package com.example.bgcpromogearreworked.api.orders.inventorymanagement;

import com.example.bgcpromogearreworked.persistence.entities.Order;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
class StatusDeltaKey {
    private final Order.Status current;
    private final Order.Status previous;
}
