package com.example.bgcpromogearreworked.api.products.images.dto.secured;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredProductImageBatchResponse {

    private final List<SecuredProductImageResponse> images;

}
