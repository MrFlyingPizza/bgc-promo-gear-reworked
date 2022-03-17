package com.example.bgcpromogearreworked.api.products.images.dto.general;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralProductImageBatchResponse {

    private final List<GeneralProductImageResponse> images;

}
