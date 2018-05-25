package exception;

public class ConnectionException extends RuntimeException{

    public ConnectionException(String error){
        super(error);
    }
}
