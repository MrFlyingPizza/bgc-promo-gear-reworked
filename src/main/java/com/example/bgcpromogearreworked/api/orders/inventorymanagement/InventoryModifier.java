package com.example.bgcpromogearreworked.api.orders.inventorymanagement;


import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;

interface InventoryModifier {
    InventoryLevel apply(InventoryLevel inventoryLevel, Integer change);
}