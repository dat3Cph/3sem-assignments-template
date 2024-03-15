package JavalinAndCrud.utils;

import JavalinAndCrud.daos.SecurityDAO;
import JavalinAndCrud.dtos.UserDTO;
import JavalinAndCrud.exceptions.ApiException;
import JavalinAndCrud.exceptions.NotAuthorizedException;
import JavalinAndCrud.exceptions.ValidationException;
import JavalinAndCrud.model.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;
import java.util.NoSuchElementException;

public class TokenUtils {

    public static String createToken(UserDTO user, String issuer, String token_expire_time, String secret_key) throws ApiException {
        try {
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issuer(issuer)
                    .claim("username", user.getUsername())
                    .claim("roles", user.getRolesAsStrings().stream().reduce((s1, s2) -> s1 + "," + s2).get())
                    .expirationTime(new Date(new Date().getTime() + Integer.parseInt(token_expire_time)))
                    .build();
            Payload payload = new Payload(claimsSet.toJSONObject());

            JWSSigner signer = new MACSigner(secret_key);
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
            JWSObject jwsobject = new JWSObject(jwsHeader, payload);
            jwsobject.sign(signer);
            return jwsobject.serialize();

        } catch (JOSEException | NoSuchElementException e) {
            e.printStackTrace();
            throw new ApiException(500, "Could not create token");
        }
    }

    public static boolean tokenNotExpired(String token) throws ParseException, NotAuthorizedException {
        if(timeToExpire(token) > 0){
            return true;
        } else {
            throw new NotAuthorizedException(403, "Token has Expired");
        }
    }

    private static int timeToExpire(String token) throws ParseException, NotAuthorizedException {
        SignedJWT jwt = SignedJWT.parse(token);
        return (int) (jwt.getJWTClaimsSet().getExpirationTime().getTime() - new Date().getTime());
    }

    public static boolean tokenIsValid(String token, String secret) throws ParseException, NotAuthorizedException, KeyLengthException {
        JWTClaimsSet claimsSet = JWTClaimsSet.parse(token);
        return true;
    }

    public static UserDTO getUserWithRolesFromToken(String token) {
        try {
            JWTClaimsSet claims = JWTClaimsSet.parse(token);
            String user = claims.getStringClaim("user");
            String pass = claims.getStringClaim("password");
            SecurityDAO dao = SecurityDAO.getInstance(false);
            User userEntity = dao.getVerifiedUser(user, pass);
            UserDTO dto = new UserDTO();
            dto.setUsername(userEntity.getUsername());
            dto.setPassword(userEntity.getPassword());
            dto.setRoles(userEntity.getRoles());
            return dto;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

}
