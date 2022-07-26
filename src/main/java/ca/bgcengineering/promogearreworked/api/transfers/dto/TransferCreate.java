package ca.bgcengineering.promogearreworked.api.transfers.dto;

import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.officelocationexists.OfficeLocationExists;
import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.productvariantexists.ProductVariantExists;
import ca.bgcengineering.promogearreworked.api.shared.validation.groups.FirstValidationGroup;
import ca.bgcengineering.promogearreworked.api.shared.validation.groups.SecondValidationGroup;
import ca.bgcengineering.promogearreworked.api.transfers.dto.validation.SufficientTransferQuantity;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;

@GroupSequence({TransferCreate.class, FirstValidationGroup.class, SecondValidationGroup.class})
@SufficientTransferQuantity(groups = SecondValidationGroup.class)
public record TransferCreate(
        @OfficeLocationExists(groups = FirstValidationGroup.class) long originId,
        @OfficeLocationExists(groups = FirstValidationGroup.class) long destinationId,
        @ProductVariantExists(groups = FirstValidationGroup.class) long variantId,
        @Min(value = 1, groups = FirstValidationGroup.class) int quantity,
        String comments
) {
}
