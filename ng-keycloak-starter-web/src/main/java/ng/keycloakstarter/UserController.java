package ng.keycloakstarter;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.Set;

@RestController()
@RequestMapping("/user")
public class UserController {

    @PostConstruct
    public void init(){
        System.out.println("TEST TEST TEST");
    }

    @GetMapping(path = "/info")
    public String helloUser(Principal principal) {
        AccessToken accessToken = getAccessToken((KeycloakAuthenticationToken) principal);
        Set<String> roles = accessToken.getRealmAccess().getRoles();
        String name = accessToken.getPreferredUsername();
        return "Hello, "+ name + " Your roles " + roles;
    }

    private AccessToken getAccessToken(KeycloakAuthenticationToken principal) {
        return principal.getAccount().getKeycloakSecurityContext().getToken();
    }
}
