package com.example.bgcpromogearreworked.api.options.option.dto.secured;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredOptionBatchResponse {

    private final List<SecuredOptionResponse> options;

}
