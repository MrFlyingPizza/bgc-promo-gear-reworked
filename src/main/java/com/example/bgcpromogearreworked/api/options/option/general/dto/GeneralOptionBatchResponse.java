package com.example.bgcpromogearreworked.api.options.option.general.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralOptionBatchResponse {

    private final List<GeneralOptionResponse> options;

}
