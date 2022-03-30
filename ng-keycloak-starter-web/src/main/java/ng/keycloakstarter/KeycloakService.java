package ng.keycloakstarter;

import lombok.extern.slf4j.Slf4j;
import ng.keycloakstarter.config.Credentials;
import ng.keycloakstarter.config.KeycloakConfig;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class KeycloakService {


    @Value( "${keycloak.realm}" )
    private String realm;

    @Value( "${keycloak.auth-server-url}" )
    private String url;

    @PostConstruct
    public void init(){
        System.out.println("URL");
        System.out.println(realm);
        System.out.println(url);
    }

    public void addUser(UserDTO userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        UsersResource userResource = getUsersResource();

        // Create user (requires manage-users role)
        try (Response response = userResource.create(user)) {
            try {
                log.info("Status returned {}", response.getStatus());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public void deleteUser(String userId){
        getUsersResource().get(userId)
                .remove();
    }

    public void addRole(String role, String userId){
        RoleRepresentation roleRepresentationToAdd = getRolesResource().list().stream().filter(roleRepresentation -> roleRepresentation.getName().equals(role)).findFirst().orElseThrow(NotFoundException::new);
        getUsersResource().get(userId).roles().realmLevel().add(List.of(roleRepresentationToAdd));
    }

    public void removeRole(String role, String userId){
        RoleRepresentation roleRepresentationToAdd = getRolesResource().list().stream().filter(roleRepresentation -> roleRepresentation.getName().equals(role)).findFirst().orElseThrow(NotFoundException::new);
        getUsersResource().get(userId).roles().realmLevel().remove(List.of(roleRepresentationToAdd));
    }

    public List<UserRepresentation> getUser(String userName){
        return getUsersResource().search(userName, true);
    }

    public void updateUser(String userId, UserDTO userDTO){
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).update(user);
    }

    public void sendVerificationLink(String userId){
        UsersResource usersResource = getUsersResource();
        usersResource.get(userId)
                .sendVerifyEmail();
    }

    public void sendResetPassword(String userId){
        getUsersResource().get(userId)
                .executeActionsEmail(List.of("UPDATE_PASSWORD"));
    }

    private UsersResource getUsersResource() {
        return getRealmResource().users();
    }

    private RealmResource getRealmResource() {
        return KeycloakConfig.getInstance().realm(realm);
    }

    private RolesResource getRolesResource() {
        return getRealmResource().roles();
    }
}
