package ca.bgcengineering.promogearreworked.api.users.user.general;

import ca.bgcengineering.promogearreworked.api.shared.utils.Utils;
import ca.bgcengineering.promogearreworked.api.users.user.general.dto.GeneralUserMapper;
import ca.bgcengineering.promogearreworked.api.users.user.general.dto.GeneralUserResponse;
import ca.bgcengineering.promogearreworked.api.users.exceptions.UserAuthenticationClaimInvalidException;
import ca.bgcengineering.promogearreworked.api.users.exceptions.UserNotAuthenticatedException;
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

    @GetMapping("/me")
    private GeneralUserResponse getUser(@AuthenticationPrincipal OidcUser oidcUser) {
        if (oidcUser == null) {
            throw new UserNotAuthenticatedException();
        }
        UUID oid = Utils.oidFromOidcUser(oidcUser);
        if (oid == null) {
            throw new UserAuthenticationClaimInvalidException();
        }
        return mapper.toResponse(handlerService.handleGetUser(oid));
    }

    // TODO: 2022-04-10 implement user updating office location

}
