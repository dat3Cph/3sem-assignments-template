package JavalinAndCrud.controllers;


import JavalinAndCrud.daos.SecurityDAO;
import JavalinAndCrud.dtos.UserDTO;
import JavalinAndCrud.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.http.Handler;

public class SecurityController {

    static ObjectMapper objectMapper = new ObjectMapper();

    static SecurityDAO securityDAO = SecurityDAO.getInstance();

    public static Handler login(){
        return ctx -> {
            ObjectNode returnObject = objectMapper.createObjectNode();
            try{
                UserDTO user = ctx.bodyAsClass(UserDTO.class);
                System.out.println("USER IN LOGIN: "+ user);

                User verifiedUserEntity =  securityDAO.getVerifiedUser(user.getUsername(), user.getPassword());
                String token = createToken(new UserDTO(verifiedUserEntity));
                ctx.status(200).json(new TokenDTO(token, user.getUsername()));
            }
        };
    }



    public static Handler register(){
       return ctx -> {};
    }
}
