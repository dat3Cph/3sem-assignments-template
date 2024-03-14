package JavalinAndCrud.daos;

import JavalinAndCrud.model.Role;
import JavalinAndCrud.model.User;
import io.javalin.validation.ValidationException;

public interface ISecurityDAO {

    User getVerifiedUser(String username, String password) throws ValidationException;
    User createUser(String username, String password);
    Role createRole(String role);
    User getUser(String username);
    User addUserRole(String username, String role);
    User removeUserRole(String username, String role);
    boolean hasRole(String role, User userEntity);

}
