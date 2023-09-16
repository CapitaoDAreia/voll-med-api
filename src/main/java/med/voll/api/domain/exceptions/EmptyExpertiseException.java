package med.voll.api.domain.exceptions;

public class EmptyExpertiseException extends RuntimeException{
    public EmptyExpertiseException(String message){
        super(message);
    }
}
