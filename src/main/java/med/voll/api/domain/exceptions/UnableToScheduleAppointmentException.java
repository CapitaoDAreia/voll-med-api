package med.voll.api.domain.exceptions;

public class UnableToScheduleAppointmentException extends RuntimeException{
    public UnableToScheduleAppointmentException(String message){
        super(message);
    }
}
