package com.example.bgcpromogearreworked.api.users.user.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredUserBatchResponse {

    private final List<SecuredUserResponse> users;
    private final Integer pageCount;
    private final Integer pageNumber;
    private final Boolean isFirstPage;
    private final Boolean isLastPage;
    private final Integer pageElementCount;
    private final Long totalElementCount;
    private final Boolean isSorted;

}
