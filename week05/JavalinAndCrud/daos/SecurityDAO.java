package JavalinAndCrud.daos;

import JavalinAndCrud.model.Role;
import JavalinAndCrud.model.User;
import io.javalin.validation.ValidationException;

public class SecurityDAO implements ISecurityDAO {


    @Override
    public User getVerifiedUser(String username, String password) throws ValidationException {
        return null;
    }

    @Override
    public User createUser(String username, String password) {
        return null;
    }

    @Override
    public Role createRole(String role) {
        return null;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public User addUserRole(String username, String role) {
        return null;
    }

    @Override
    public User removeUserRole(String username, String role) {
        return null;
    }

    @Override
    public boolean hasRole(String role, User userEntity) {
        return false;
    }
}
