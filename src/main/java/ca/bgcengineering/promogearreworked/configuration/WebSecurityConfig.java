package ca.bgcengineering.promogearreworked.configuration;

import com.azure.spring.aad.webapp.AADWebSecurityConfigurerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends AADWebSecurityConfigurerAdapter {

    private final MSGraphSyncedUserService oidcUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .oauth2Login().userInfoEndpoint().oidcUserService(oidcUserService)
                .and().and()
                .headers().frameOptions().sameOrigin()
        ;
    }

}
