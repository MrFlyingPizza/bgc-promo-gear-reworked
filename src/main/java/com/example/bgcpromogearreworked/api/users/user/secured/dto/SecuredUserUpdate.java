package com.example.bgcpromogearreworked.api.users.user.secured.dto;

import com.example.bgcpromogearreworked.api.shared.validation.constraints.officelocationexists.OfficeLocationExists;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class SecuredUserUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Digits(integer = 8, fraction = 2)
    private final BigDecimal credit;

    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Digits(integer = 8, fraction = 2)
    private final BigDecimal owedCredit;

    @NotNull
    private final Instant lastBigItemDate;

    @NotNull
    @OfficeLocationExists
    private final Long officeId;

    @JsonCreator
    SecuredUserUpdate(@JsonProperty("credit") BigDecimal credit,
                      @JsonProperty("owedCredit") BigDecimal owedCredit,
                      @JsonProperty("lastBigItemDate") Instant lastBigItemDate,
                      @JsonProperty("officeId") Long officeId) {
        this.credit = credit;
        this.owedCredit = owedCredit;
        this.lastBigItemDate = lastBigItemDate;
        this.officeId = officeId;
    }
}
