package com.example.bgcpromogearreworked.api.products.variant.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredProductVariantBatchResponse {

    private final List<SecuredProductVariantResponse> variants;

}
