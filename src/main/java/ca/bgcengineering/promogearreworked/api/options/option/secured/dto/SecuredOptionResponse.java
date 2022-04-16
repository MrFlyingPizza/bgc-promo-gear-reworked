package ca.bgcengineering.promogearreworked.api.options.option.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredOptionResponse {

    @Getter
    @RequiredArgsConstructor
    static class NestedOptionValue {
        private final Long id;
        private final String value;
    }

    private final Long id;
    private final String name;
    private final List<NestedOptionValue> values;

}
