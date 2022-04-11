package ca.bgcengineering.promogearreworked.api.products.variant.general.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralProductVariantBatchResponse {

    private final List<GeneralProductVariantResponse> variants;

}
