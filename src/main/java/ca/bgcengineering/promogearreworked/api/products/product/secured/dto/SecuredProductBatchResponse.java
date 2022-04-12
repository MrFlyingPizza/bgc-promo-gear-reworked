package ca.bgcengineering.promogearreworked.api.products.product.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredProductBatchResponse {

    private final List<SecuredProductResponse> products;

}
