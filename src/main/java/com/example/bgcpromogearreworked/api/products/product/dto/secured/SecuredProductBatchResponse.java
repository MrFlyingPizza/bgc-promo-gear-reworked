package com.example.bgcpromogearreworked.api.products.product.dto.secured;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredProductBatchResponse {

    private final List<SecuredProductResponse> products;

}
