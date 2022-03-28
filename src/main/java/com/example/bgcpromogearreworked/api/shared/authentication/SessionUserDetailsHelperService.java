package com.example.bgcpromogearreworked.api.shared.authentication;

import com.example.bgcpromogearreworked.api.shared.utils.Utils;
import com.example.bgcpromogearreworked.api.users.exceptions.UserAuthenticationClaimInvalidException;
import com.example.bgcpromogearreworked.api.users.exceptions.UserNotAuthenticatedException;
import com.example.bgcpromogearreworked.api.users.exceptions.UserNotFoundException;
import com.example.bgcpromogearreworked.api.users.user.UserService;
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
