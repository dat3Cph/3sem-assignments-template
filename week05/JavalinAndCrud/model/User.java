package JavalinAndCrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity(name = "users")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class User implements ISecurityUser{

    @Id
    private String username;
    private String password;

    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_name",referencedColumnName = "username")},
            inverseJoinColumns = {
            @JoinColumn(name = "role_name",referencedColumnName = "name")})
    @ManyToMany
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password){
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User(String username, Set<Role> roles){
        this.username = username;
        this.roles = roles;
    }
    @JsonIgnore
    @Override
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

    @Override
    public boolean verifyPassword(String pw) {
         return BCrypt.checkpw(pw,this.password);
    }

    @Override
    public void addRole(Role role) {
        if(role != null){
            roles.add(role);
        }
    }

    @Override
    public void removeRole(String role) {
        List<Role> roles = this.roles.stream().filter(x -> x.getName().equals(role)).toList();
        if(roles.get(0).getName().equals(role)){
            this.roles.remove(roles.get(0));
        }
    }
}
