package ca.bgcengineering.promogearreworked.configuration;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.spring.cloud.autoconfigure.aad.properties.AadAuthenticationProperties;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.requests.GraphServiceClient;
import lombok.RequiredArgsConstructor;
import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class GraphServiceConfig {

    private final static String SCOPE = "https://graph.microsoft.com/.default";

    private final AadAuthenticationProperties appAuthProperties;

    @Bean("graphClient")
    public GraphServiceClient<Request> getGraphServiceClient() {
        final ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId(appAuthProperties.getCredential().getClientId())
                .clientSecret(appAuthProperties.getCredential().getClientSecret())
                .tenantId(appAuthProperties.getProfile().getTenantId())
                .build();

        List<String> scopes = new ArrayList<>();
        scopes.add(SCOPE);

        final TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(scopes, clientSecretCredential);

        return GraphServiceClient
                .builder()
                .authenticationProvider(tokenCredentialAuthProvider)
                .buildClient();
    }

}
