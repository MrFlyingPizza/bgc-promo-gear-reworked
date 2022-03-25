package com.example.bgcpromogearreworked.api.users.user.general;

import com.example.bgcpromogearreworked.api.shared.utils.Utils;
import com.example.bgcpromogearreworked.api.users.exceptions.UserNotAuthenticatedException;
import com.example.bgcpromogearreworked.api.users.user.general.dto.GeneralUserMapper;
import com.example.bgcpromogearreworked.api.users.user.general.dto.GeneralUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GeneralUserController {

    private final GeneralUserHandlerService handlerService;
    private final GeneralUserMapper mapper;

    @GetMapping
    private GeneralUserResponse getUser(@AuthenticationPrincipal OidcUser oidcUser) {
        UUID oid = Utils.oidFromOidcUser(oidcUser);
        if (oid == null) {
            throw new UserNotAuthenticatedException();
        }
        return mapper.toResponse(handlerService.handleGetUser(oid));
    }

}
