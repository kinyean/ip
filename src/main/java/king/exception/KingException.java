package king.exception;

/**
 * Exception that is to be thrown only for this chatbot
 */
public class KingException extends Exception{

    public KingException(String message){
        super(message);
    }
}
