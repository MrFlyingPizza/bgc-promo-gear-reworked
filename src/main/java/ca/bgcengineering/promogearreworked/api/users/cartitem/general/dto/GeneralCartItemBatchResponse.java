package ca.bgcengineering.promogearreworked.api.users.cartitem.general.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralCartItemBatchResponse {

    private final List<GeneralCartItemResponse> cartItems;

}
