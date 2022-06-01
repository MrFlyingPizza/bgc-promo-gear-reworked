package ca.bgcengineering.promogearreworked.configuration;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Duration;

@Getter
@Configuration
public class StoreDefaultsConfig {

    private final BigDecimal startingCredits = new BigDecimal(500);
    private final Duration bigItemWaitDuration = Duration.ofDays(365);
    private final int bigItemOrderQuantityLimit = 1;

}
