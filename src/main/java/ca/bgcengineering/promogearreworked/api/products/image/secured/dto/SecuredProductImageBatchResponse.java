package ca.bgcengineering.promogearreworked.api.products.image.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredProductImageBatchResponse {

    private final List<SecuredProductImageResponse> images;

}
