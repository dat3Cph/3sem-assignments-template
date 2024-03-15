package JavalinAndCrud.dtos;

import JavalinAndCrud.model.Role;
import JavalinAndCrud.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();

    public UserDTO(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        if(user.getRoles() != null){
            this.roles = user.getRoles();
        }
    }

    @JsonIgnore
    public Set<String> getRolesAsStrings() {
        if(roles.isEmpty()){
            return null;
        }
        Set<String> rolesAsString = new HashSet<>();
        roles.forEach((role) -> {
            rolesAsString.add(role.getName());
        });
        return rolesAsString;
    }

}
