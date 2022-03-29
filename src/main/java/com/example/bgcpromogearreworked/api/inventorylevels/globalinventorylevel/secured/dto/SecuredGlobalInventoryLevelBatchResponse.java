package com.example.bgcpromogearreworked.api.inventorylevels.globalinventorylevel.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredGlobalInventoryLevelBatchResponse {

    private final List<SecuredGlobalInventoryLevelResponse> globalInventoryLevels;

}
