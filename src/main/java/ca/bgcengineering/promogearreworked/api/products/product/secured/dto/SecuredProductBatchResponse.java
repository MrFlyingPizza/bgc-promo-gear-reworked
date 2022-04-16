package ca.bgcengineering.promogearreworked.api.products.product.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredProductBatchResponse {

    private final List<SecuredProductResponse> products;
    private final Integer pageCount;
    private final Integer pageNumber;
    private final Boolean isFirstPage;
    private final Boolean isLastPage;
    private final Integer pageElementCount;
    private final Long totalElementCount;
    private final Boolean isSorted;
}
