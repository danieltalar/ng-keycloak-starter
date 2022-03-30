package ng.keycloakstarter.config;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {


    @Value( "${app.admin.realm}" )
    private static String realm;
    @Value( "${app.admin.clientId}" )
    private static String clientId;
    @Value( "${app.admin.user}" )
    private static String user;
    @Value( "${app.admin.password}" )
    private static String password;
    @Value( "${keycloak.auth-server-url}" )
    private static String serverUrl;

    @Bean
    public static Keycloak getInstance(){
            return Keycloak.getInstance(
                    serverUrl,
                    realm,
                    user,
                    password,
                    clientId);
        }
}