package com.example.bgcpromogearreworked.api.products.image.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SecuredProductImageResponse {

    private final Long id;
    private final String src;
    private final String alt;
    private final Integer position;

}
