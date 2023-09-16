package med.voll.api.domain.exceptions;

public class UnableToCancelAppointmentException extends RuntimeException{
    public UnableToCancelAppointmentException(String message){
        super(message);
    }
}
