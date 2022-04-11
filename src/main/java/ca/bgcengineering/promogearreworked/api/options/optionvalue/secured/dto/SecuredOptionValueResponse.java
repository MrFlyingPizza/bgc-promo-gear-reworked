package ca.bgcengineering.promogearreworked.api.options.optionvalue.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SecuredOptionValueResponse {

    private final Long id;
    private final String value;

}
