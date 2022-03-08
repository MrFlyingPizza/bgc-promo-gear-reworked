package com.example.bgcpromogearreworked.api.categories.category.dto.general;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralCategoryBatchResponse {

    private final List<GeneralCategoryResponse> categories;

}
