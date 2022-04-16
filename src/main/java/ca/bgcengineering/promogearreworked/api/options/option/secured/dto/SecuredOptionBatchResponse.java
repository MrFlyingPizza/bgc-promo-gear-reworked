package ca.bgcengineering.promogearreworked.api.options.option.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredOptionBatchResponse {

    private final List<SecuredOptionResponse> options;

}
