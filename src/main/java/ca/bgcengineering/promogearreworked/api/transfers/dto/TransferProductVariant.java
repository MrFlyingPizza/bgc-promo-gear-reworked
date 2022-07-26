package ca.bgcengineering.promogearreworked.api.transfers.dto;

import java.util.Map;

public record TransferProductVariant(long productId, long variantId, String productName, Map<String, String> options) {
}
