package ca.bgcengineering.promogearreworked.api.options.optionvalue.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SecuredOptionValueResponse {

    private final Long valueId;
    private final Long optionId;
    private final String name;
    private final String value;

}
