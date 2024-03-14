package JavalinAndCrud.dtos;

import JavalinAndCrud.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

    private String username;
    private String password;

    public UserDTO(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
    }



}
