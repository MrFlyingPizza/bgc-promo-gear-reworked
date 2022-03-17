package com.example.bgcpromogearreworked.api.products.images.dto.general;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GeneralProductImageResponse {

    private final Long id;
    private final String src;
    private final String alt;
    private final Integer position;

}
