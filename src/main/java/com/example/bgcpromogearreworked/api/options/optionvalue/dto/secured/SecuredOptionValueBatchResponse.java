package com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredOptionValueBatchResponse {

    private final List<String> values;

}
