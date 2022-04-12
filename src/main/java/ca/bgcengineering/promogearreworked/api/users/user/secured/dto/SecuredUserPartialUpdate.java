package ca.bgcengineering.promogearreworked.api.users.user.secured.dto;

import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.officelocationexists.OfficeLocationExists;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class SecuredUserPartialUpdate {
    @JsonIgnore
    @Setter
    private Long id;

    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Digits(integer = 8, fraction = 2)
    private final BigDecimal credit;

    @DecimalMin("0.00")
    @DecimalMax("99999999.99")
    @Digits(integer = 8, fraction = 2)
    private final BigDecimal owedCredit;

    private final Instant lastBigItemDate;

    @OfficeLocationExists
    private final Long locationId;

    @JsonCreator
    SecuredUserPartialUpdate(@JsonProperty("credit") BigDecimal credit,
                             @JsonProperty("owedCredit") BigDecimal owedCredit,
                             @JsonProperty("lastBigItemDate") Instant lastBigItemDate,
                             @JsonProperty("locationId") Long locationId) {
        this.credit = credit;
        this.owedCredit = owedCredit;
        this.lastBigItemDate = lastBigItemDate;
        this.locationId = locationId;
    }
}
