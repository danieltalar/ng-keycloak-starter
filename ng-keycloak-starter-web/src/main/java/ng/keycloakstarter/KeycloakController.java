package ng.keycloakstarter;

import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "admin/user")
public class KeycloakController {

    private final KeycloakService service;

    @PostMapping
    public String addUser(@RequestBody UserDTO userDTO){
        service.addUser(userDTO);
        return "User Added Successfully.";
    }

    @GetMapping
    public String addddUser(){
    //    service.addUser(userDTO);
        return "User Added dddSuccessfully.";
    }

    @GetMapping(path = "/{userName}")
    public List<UserRepresentation> getUser(@PathVariable("userName") String userName){
        return service.getUser(userName);
    }

    @PutMapping(path = "/update/{userId}")
    public String updateUser(@PathVariable("userId") String userId,   @RequestBody UserDTO userDTO){
        service.updateUser(userId, userDTO);
        return "User Details Updated Successfully.";
    }

    @DeleteMapping(path = "/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
        service.deleteUser(userId);
        return "User Deleted Successfully.";
    }

    @GetMapping(path = "/verification-link/{userId}")
    public String sendVerificationLink(@PathVariable("userId") String userId){
        service.sendVerificationLink(userId);
        return "Verification Link Send to Registered E-mail Id.";
    }

    @GetMapping(path = "/reset-password/{userId}")
    public String sendResetPassword(@PathVariable("userId") String userId){
        service.sendResetPassword(userId);
        return "Reset Password Link Send Successfully to Registered E-mail Id.";
    }

    @PutMapping(path = "/roles/{userId}/{role}")
    public String addRoleToUser(@PathVariable("userId") String userId, @PathVariable("role") String role){
        service.addRole(role, userId);
        return "Role added successfully";
    }

    @DeleteMapping(path = "/roles/{userId}/{role}")
    public String removeRoleFromUser(@PathVariable("userId") String userId, @PathVariable("role") String role){
        service.removeRole(role, userId);
        return "Role removed successfully";
    }
}