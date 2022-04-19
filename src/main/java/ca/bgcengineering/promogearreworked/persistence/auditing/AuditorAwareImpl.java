package ca.bgcengineering.promogearreworked.persistence.auditing;

import ca.bgcengineering.promogearreworked.configuration.DbBackedUser;
import ca.bgcengineering.promogearreworked.persistence.entities.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    @NonNull
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        if (authentication.getPrincipal() instanceof DbBackedUser) {
            return Optional.ofNullable(((DbBackedUser) authentication.getPrincipal()).getUser());
        }
        return Optional.empty();
    }
}
