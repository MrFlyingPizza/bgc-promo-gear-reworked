package ca.bgcengineering.promogearreworked.configuration;

import ca.bgcengineering.promogearreworked.persistence.repositories.UserRepository;
import com.azure.spring.cloud.autoconfigure.aad.implementation.webapp.AadOAuth2UserService;
import com.azure.spring.cloud.autoconfigure.aad.properties.AadAuthenticationProperties;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import okhttp3.Request;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MSGraphSyncedUserService extends AadOAuth2UserService {

    private final UserRepository userRepo;
    private final GraphServiceClient<Request> graphClient;
    private final StoreDefaultsConfig storeDefaults;

    public MSGraphSyncedUserService(AadAuthenticationProperties properties,
                                    UserRepository userRepo,
                                    GraphServiceClient<Request> graphClient, StoreDefaultsConfig storeDefaults) {
        super(properties);
        this.userRepo = userRepo;
        this.graphClient = graphClient;
        this.storeDefaults = storeDefaults;
    }

    @Override
    public DbBackedUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        User graphUser = graphClient.users(userRequest.getIdToken().getClaim("oid")).buildRequest().get();
        if (graphUser == null) {
            throw new OAuth2AuthenticationException("Could not obtain the information for the user to be authenticated.");
        }
        assert graphUser.id != null; // this should never be null as specified by Microsoft documentation.
        // TODO: 2022-05-30 Throw authentication exception and deny authentication if email not present
        UUID oid = UUID.fromString(graphUser.id);
        ca.bgcengineering.promogearreworked.persistence.entities.User dbUser = userRepo.findByOid(oid).map(user -> {
            user.setDisplayName(graphUser.displayName);
            user.setEmail(graphUser.mail);
            return user;
        }).orElseGet(() -> {
            ca.bgcengineering.promogearreworked.persistence.entities.User user = new ca.bgcengineering.promogearreworked.persistence.entities.User();
            user.setOid(UUID.fromString(graphUser.id));
            user.setDisplayName(graphUser.displayName);
            user.setEmail(graphUser.mail);
            user.setCredit(storeDefaults.getStartingCredits());
            return user;
        });
        userRepo.saveAndFlush(dbUser);
        OidcUser oidcUser = super.loadUser(userRequest);
        return new DbBackedUser(dbUser, oidcUser);
    }
}
