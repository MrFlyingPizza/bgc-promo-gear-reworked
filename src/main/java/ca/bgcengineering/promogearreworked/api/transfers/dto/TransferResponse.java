package ca.bgcengineering.promogearreworked.api.transfers.dto;

import java.time.Instant;

public record TransferResponse(
        long id,
        TransferProductVariant variant,
        TransferOfficeLocation origin,
        TransferOfficeLocation destination,
        String comments,
        int quantity,
        Instant createdDate,
        TransferUser createdBy
) {
}
