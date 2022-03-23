package com.example.bgcpromogearreworked.configuration;

import com.azure.spring.aad.webapp.AADOAuth2UserService;
import com.azure.spring.autoconfigure.aad.AADAuthenticationProperties;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MSGraphSyncedUserService extends AADOAuth2UserService {

    private final GraphServiceClient graphClient;

    public MSGraphSyncedUserService(AADAuthenticationProperties properties, GraphServiceClient graphClient) {
        super(properties);
        this.graphClient = graphClient;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        UUID oid = UUID.fromString(userRequest.getIdToken().getClaim("oid"));
        User user = graphClient.me().buildRequest().get();
        // TODO: 2022-03-21 finish implement adding to database and synchronization
        return super.loadUser(userRequest);
    }
}
