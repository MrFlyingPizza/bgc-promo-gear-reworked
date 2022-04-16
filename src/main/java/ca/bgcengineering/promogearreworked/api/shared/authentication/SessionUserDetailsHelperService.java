package ca.bgcengineering.promogearreworked.api.shared.authentication;

import ca.bgcengineering.promogearreworked.api.shared.utils.Utils;
import ca.bgcengineering.promogearreworked.api.users.exceptions.UserAuthenticationClaimInvalidException;
import ca.bgcengineering.promogearreworked.api.users.exceptions.UserNotAuthenticatedException;
import ca.bgcengineering.promogearreworked.api.users.exceptions.UserNotFoundException;
import ca.bgcengineering.promogearreworked.api.users.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionUserDetailsHelperService {

    private final UserService userService;

    public Long processAuthenticatedUser(OidcUser oidcUser) {
        final String NO_OID = "No object ID claim found in the authenticated user.";
        if (oidcUser == null) {
            throw new UserNotAuthenticatedException();
        }
        UUID oid = Utils.oidFromOidcUser(oidcUser);
        if (oid == null) {
            throw new UserAuthenticationClaimInvalidException(NO_OID);
        }
        if (!userService.checkUserExists(oid)) {
            throw new UserNotFoundException();
        }
        return userService.getUser(oid).getId();
    }
}
