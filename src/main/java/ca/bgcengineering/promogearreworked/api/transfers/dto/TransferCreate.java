package ca.bgcengineering.promogearreworked.api.transfers.dto;

import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.officelocationexists.OfficeLocationExists;
import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.productvariantexists.ProductVariantExists;

import javax.validation.constraints.Min;

public record TransferCreate(
        @OfficeLocationExists long originId,
        @OfficeLocationExists long destinationId,
        @ProductVariantExists long variantId,
        @Min(1) int quantity,
        String comments
) {
}
