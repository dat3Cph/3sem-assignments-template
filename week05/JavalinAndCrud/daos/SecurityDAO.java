package JavalinAndCrud.daos;

import JavalinAndCrud.exceptions.ValidationException;
import JavalinAndCrud.model.Role;
import JavalinAndCrud.model.User;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class SecurityDAO extends DAO<User, String> implements ISecurityDAO {

    private static SecurityDAO instance;

    private SecurityDAO(Class<User> userClass, boolean isTesting) {
        super(userClass, isTesting);
    }

    public static SecurityDAO getInstance(boolean isTesting){
        if(instance == null){
            instance = new SecurityDAO(User.class, isTesting);
        }
        return instance;
    }


    @Override
    public User getVerifiedUser(String username, String password) throws ValidationException {
        try(var em = emf.createEntityManager()){
            System.out.println("USERNAME INSIDE GET_VERIFIED_USER: "+ username + "PASSWORD: " + password);
            User user = em.find(User.class, username);
            if(user == null){
                throw new EntityNotFoundException("No user found with username: "+ username);
            }
            user.getRoles().size();
            if(!user.verifyPassword(password)){
                throw new ValidationException("Wrong password");
            }
            return user;
        }
    }

    @Override
    public User createUser(String username, String password) {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User userEntity = new User(username, password);
            userEntity.addRole(em.find(Role.class, "USER"));
            em.persist(userEntity);
            em.getTransaction().commit();
            return userEntity;
        }
    }

    @Override
    public Role createRole(String role) {
        try(var em = emf.createEntityManager()){
            Role roleEntity = em.find(Role.class, role);
            if(roleEntity != null){
                return roleEntity;
            }
        }
        throw new EntityNotFoundException("Couldn't find role");
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
