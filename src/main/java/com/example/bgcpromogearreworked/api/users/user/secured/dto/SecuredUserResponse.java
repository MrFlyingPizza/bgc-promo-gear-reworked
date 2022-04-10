package com.example.bgcpromogearreworked.api.users.user.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class SecuredUserResponse {

    @Getter
    @RequiredArgsConstructor
    static class NestedOfficeLocation {
        private final Long id;
        private final String name;
    }

    private final Long id;
    private final UUID oid;
    private final BigDecimal credit;
    private final BigDecimal owedCredit;
    private final Instant lastBigItemDate;
    private final String displayName;
    private final NestedOfficeLocation location;

}
