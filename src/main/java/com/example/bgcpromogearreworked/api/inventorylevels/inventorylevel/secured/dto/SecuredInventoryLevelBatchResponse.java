package com.example.bgcpromogearreworked.api.inventorylevels.inventorylevel.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredInventoryLevelBatchResponse {

    private final List<SecuredInventoryLevelResponse> inventoryLevels;

}
