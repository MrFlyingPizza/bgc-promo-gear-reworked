package com.example.bgcpromogearreworked.api.users.user.secured.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class SecuredUserUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    private final BigDecimal credit;
    private final Instant lastBigItemDate;
    private final Long officeId;

    @JsonCreator
    SecuredUserUpdate(@JsonProperty("credit") BigDecimal credit,
                      @JsonProperty("lastBigItemDate") Instant lastBigItemDate,
                      @JsonProperty("officeId") Long officeId) {
        this.credit = credit;
        this.lastBigItemDate = lastBigItemDate;
        this.officeId = officeId;
    }
}
