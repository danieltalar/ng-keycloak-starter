package ng.keycloakstarter.config;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {


    @Value( "${app.admin.realm}" )
    private String realm;
    @Value( "${app.admin.clientId}" )
    private String clientId;
    @Value( "${app.admin.user}" )
    private String user;
    @Value( "${app.admin.password}" )
    private String password;
    @Value( "${keycloak.auth-server-url}" )
    private String serverUrl;

    @Bean
    public Keycloak getInstance(){
            return Keycloak.getInstance(
                    serverUrl,
                    realm,
                    user,
                    password,
                    clientId);
        }
}