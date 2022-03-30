
package ng.keycloakstarter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String userName;
    private String email;
    private String password;
    private String firstname;
    private String lastName;
}