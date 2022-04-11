package ca.bgcengineering.promogearreworked.api.options.optionvalue.general.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralOptionValueBatchResponse {

    private final List<GeneralOptionValueResponse> values;

}
