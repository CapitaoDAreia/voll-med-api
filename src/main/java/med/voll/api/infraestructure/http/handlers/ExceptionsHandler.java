package med.voll.api.infraestructure.http.handlers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Stream;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(EntityNotFoundException ex){
        ExceptionDTO dto = new ExceptionDTO(ex.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldError> errors = ex.getFieldErrors();
        Stream<NotValidExceptionDTO> dto = errors.stream().map(NotValidExceptionDTO::new);

        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        String message = ex.getMostSpecificCause().getLocalizedMessage();
        ExceptionDTO dto = new ExceptionDTO(message);

        return ResponseEntity.badRequest().body(dto);
    }

    private record NotValidExceptionDTO(String field, String message){
        NotValidExceptionDTO(FieldError errors){
            this(errors.getField(), errors.getDefaultMessage());
        }
    }

    private record ExceptionDTO(String message){}
}
