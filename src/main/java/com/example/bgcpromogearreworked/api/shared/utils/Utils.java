package com.example.bgcpromogearreworked.api.shared.utils;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.UUID;

public class Utils {

    public static UUID oidFromOidcUser(OidcUser oidcUser) {
        UUID oid = null;
        if (oidcUser != null) {
            String oidString = oidcUser.getClaim("oid");
            if (oidString != null) {
                oid = UUID.fromString(oidString);
            }
        }
        return oid;
    }

}
