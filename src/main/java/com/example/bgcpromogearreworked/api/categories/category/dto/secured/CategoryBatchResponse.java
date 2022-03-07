package com.example.bgcpromogearreworked.api.categories.category.dto.secured;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CategoryBatchResponse {

    private final List<CategoryResponse> categories;
}
