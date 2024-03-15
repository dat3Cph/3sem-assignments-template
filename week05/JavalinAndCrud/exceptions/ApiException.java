package JavalinAndCrud.exceptions;

public class ApiException extends Throwable{
    int statusCode;
    Exception exception;
    public ApiException(int code, String msg){
        statusCode = code;
        exception = new Exception(msg);
    }
    public ApiException(String code){
        super(code);
    }
}
