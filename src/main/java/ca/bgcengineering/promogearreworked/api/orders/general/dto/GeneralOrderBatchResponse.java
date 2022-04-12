package ca.bgcengineering.promogearreworked.api.orders.general.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralOrderBatchResponse {

    private final List<GeneralOrderResponse> orders;

}
