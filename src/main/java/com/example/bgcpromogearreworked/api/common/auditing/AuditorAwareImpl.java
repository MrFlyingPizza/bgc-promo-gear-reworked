package com.example.bgcpromogearreworked.api.common.auditing;

import com.example.bgcpromogearreworked.api.users.User;
import com.example.bgcpromogearreworked.api.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<User> {

    private final UserRepository repo;

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        if (authentication.getPrincipal() instanceof OidcUser) {
            OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
            UUID oid = UUID.fromString(oidcUser.getClaim("oid"));
            return Optional.of(repo.findByOid(oid));
        }
        return Optional.empty();
    }
}
