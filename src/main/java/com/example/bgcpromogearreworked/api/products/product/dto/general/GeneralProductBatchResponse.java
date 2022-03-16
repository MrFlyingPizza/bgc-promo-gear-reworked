package com.example.bgcpromogearreworked.api.products.product.dto.general;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralProductBatchResponse {

    private final List<GeneralProductResponse> products;

}
