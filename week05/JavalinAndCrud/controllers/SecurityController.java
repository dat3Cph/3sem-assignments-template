package JavalinAndCrud.controllers;


import JavalinAndCrud.daos.SecurityDAO;
import JavalinAndCrud.dtos.TokenDTO;
import JavalinAndCrud.dtos.UserDTO;
import JavalinAndCrud.exceptions.ApiException;
import JavalinAndCrud.exceptions.ValidationException;
import JavalinAndCrud.model.Role;
import JavalinAndCrud.model.User;
import JavalinAndCrud.utils.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Set;

public class SecurityController {

    static ObjectMapper objectMapper = new ObjectMapper();

    static SecurityDAO securityDAO = SecurityDAO.getInstance(false);

    static SecurityController instance;

    public static SecurityController getInstance(){
        if(instance == null){
            instance = new SecurityController();
        }
        return instance;
    }


    public static boolean authorize(UserDTO user, Set<String> allowedRoles){
        for(Role role : user.getRoles()){
            for(String s : allowedRoles){
                if(role.getName().equals(s)){
                    return true;
                }
            }
        }
        return false;
    }


    public static Handler authenticate(){
        ObjectNode returnObject = objectMapper.createObjectNode();
        return(ctx -> {
            if(ctx.method().toString().equals("OPTIONS")){
                ctx.status(200);
                return;
            }
            String header = ctx.header("Authorization");
            if(header == null){
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Authorization header missing"));
                return;
            }
            String token = header.split(" ")[1];
            if(token == null){
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Authorization header malformed"));
            }
            UserDTO verifiedTokenUser = verifyToken(token);
            if(verifiedTokenUser == null){
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Invalid User or token"));
            }
            System.out.println("USER IN AUTHENTICATE: "+ verifiedTokenUser);
            ctx.attribute("user", verifiedTokenUser);
        });
    }

    public static UserDTO verifyToken(String token){
        return new UserDTO("lol","test");
    }

    public static Handler login(){
        return ctx -> {
            ObjectNode returnObject = objectMapper.createObjectNode();
            try{
                UserDTO user = ctx.bodyAsClass(UserDTO.class);
                System.out.println("USER IN LOGIN: "+ user);

                User verifiedUserEntity =  securityDAO.getVerifiedUser(user.getUsername(), user.getPassword());
                String token = createToken(new UserDTO(verifiedUserEntity));
                ctx.status(200).json(new TokenDTO(token, user.getUsername()));

            } catch (EntityNotFoundException | ValidationException | ApiException e){
                ctx.status(401);
                System.out.println(e.getMessage());
                ctx.json(returnObject.put("msg", e.getMessage()));
            }
        };
    }

    public static String createToken(UserDTO user) throws ApiException {
        String issuer;
        String token_expire_time;
        String secret_key;
        if(System.getenv("DEPLOYED") != null){
            issuer = System.getenv("ISSUER");
            token_expire_time = System.getenv("TOKEN_EXPIRE_TIME");
            secret_key = System.getenv("SECRET_KEY");
        } else {
            issuer = "Nicklas :)";
            token_expire_time = "3000000";
            secret_key = generateSecretKey(32);
        }
        return TokenUtils.createToken(user, issuer, token_expire_time, secret_key);
    }

    public static String generateSecretKey(int keyLengthBytes) {
        // Ensure that the key length is at least 256 bits (32 bytes)
        if (keyLengthBytes < 32) {
            throw new IllegalArgumentException("Key length must be at least 256 bits (32 bytes)");
        }

        // Generate random bytes for the secret key
        byte[] randomBytes = new byte[keyLengthBytes];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);

        // Encode the random bytes using Base64 encoding
        return Base64.getEncoder().encodeToString(randomBytes);
    }


    public static Handler register(){
       return ctx -> {
           ObjectNode returnObject = objectMapper.createObjectNode();
           try{
               UserDTO userInput = ctx.bodyAsClass(UserDTO.class);
               User created = securityDAO.createUser(userInput.getUsername(), userInput.getPassword());

               String token = createToken(new UserDTO(created));
               ctx.status(HttpStatus.CREATED).json(new TokenDTO(token, userInput.getUsername()));
           } catch (ApiException | EntityExistsException e) {
               ctx.status(HttpStatus.UNPROCESSABLE_CONTENT);
               ctx.json(returnObject.put("msg","User already exits"));
           }
       };
    }
}
