package com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredOptionValueBatchResponse {

    private final List<SecuredOptionValueResponse> values;

}
