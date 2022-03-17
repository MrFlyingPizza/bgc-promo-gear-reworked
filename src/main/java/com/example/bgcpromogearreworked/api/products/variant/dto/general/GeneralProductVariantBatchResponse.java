package com.example.bgcpromogearreworked.api.products.variant.dto.general;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralProductVariantBatchResponse {

    private final List<GeneralProductVariantResponse> variants;

}
