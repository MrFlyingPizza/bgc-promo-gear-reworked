package ca.bgcengineering.promogearreworked.configuration;

import com.azure.spring.aad.webapp.AADOAuth2UserService;
import com.azure.spring.autoconfigure.aad.AADAuthenticationProperties;
import ca.bgcengineering.promogearreworked.persistence.repositories.UserRepository;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.UUID;

@Service
public class MSGraphSyncedUserService extends AADOAuth2UserService {

    private final UserRepository userRepo;
    private final GraphServiceClient graphClient;
    private final StoreDefaultsConfig storeDefaults;

    public MSGraphSyncedUserService(AADAuthenticationProperties properties,
                                    UserRepository userRepo,
                                    GraphServiceClient graphClient, StoreDefaultsConfig storeDefaults) {
        super(properties);
        this.userRepo = userRepo;
        this.graphClient = graphClient;
        this.storeDefaults = storeDefaults;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        User graphUser = graphClient.users(userRequest.getIdToken().getClaim("oid")).buildRequest().get();
        if (graphUser == null) {
            throw new OAuth2AuthenticationException("Could not obtain the information for the user to be authenticated.");
        }
        assert graphUser.id != null; // this should never be null as specified by Microsoft documentation.
        UUID oid = UUID.fromString(graphUser.id);
        ca.bgcengineering.promogearreworked.persistence.entities.User updatedUser = userRepo.findByOid(oid).map(user -> {
            user.setDisplayName(graphUser.displayName);
            return user;
        }).orElseGet(() -> {
            ca.bgcengineering.promogearreworked.persistence.entities.User user = new ca.bgcengineering.promogearreworked.persistence.entities.User();
            user.setOid(UUID.fromString(graphUser.id));
            user.setDisplayName(graphUser.displayName);
            user.setCredit(storeDefaults.getStartingCredits());
            user.setLastBigItemDate(Clock.systemUTC().instant());
            return user;
        });
        userRepo.saveAndFlush(updatedUser);
        return super.loadUser(userRequest);
    }
}
