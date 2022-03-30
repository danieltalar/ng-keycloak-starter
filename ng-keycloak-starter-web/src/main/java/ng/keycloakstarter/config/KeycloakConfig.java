package ng.keycloakstarter.config;

import org.keycloak.admin.client.Keycloak;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    private static final String serverUrl = "http://localhost:8080/auth";
    private static final String realm = "master";
    private static final String clientId = "admin-cli";
    private static final String userName = "admin";
    private static final String password = "admin";


    @Bean
    public static Keycloak getInstance(){
            return Keycloak.getInstance(
                    serverUrl,
                    realm,
                    userName,
                    password,
                    clientId);
        }
}