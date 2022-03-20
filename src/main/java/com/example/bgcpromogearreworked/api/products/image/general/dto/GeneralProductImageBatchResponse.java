package com.example.bgcpromogearreworked.api.products.image.general.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralProductImageBatchResponse {

    private final List<GeneralProductImageResponse> images;

}
