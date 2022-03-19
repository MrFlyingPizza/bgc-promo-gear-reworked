package com.example.bgcpromogearreworked;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableSpringDataWebSupport
@OpenAPIDefinition(info = @Info(title = "BGC Promo Gear Store REST API", version = "3.0", description = "REST API to enable BGC Promo Gear store functionality and promo gear team services."))
public class BgcPromoGearReworkedApplication {

    public static void main(String[] args) {
        SpringApplication.run(BgcPromoGearReworkedApplication.class, args);
    }

}
