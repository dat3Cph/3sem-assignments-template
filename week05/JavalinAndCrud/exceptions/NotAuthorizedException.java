package JavalinAndCrud.exceptions;

public class NotAuthorizedException extends Throwable{

    public NotAuthorizedException(int code, String msg){
        super(msg+ " status code: "+code);
    }
}
