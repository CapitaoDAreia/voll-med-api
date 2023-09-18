package med.voll.api.domain.validations.cancelAppointments;

import med.voll.api.domain.dtos.appointments.CancelAppointmentDTO;
import med.voll.api.domain.exceptions.UnableToCancelAppointmentException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateAppointmentCancellationAdvance implements ValidateCancelAppointmentsBusinessRulesInterface {
    @Override
    public void validate(CancelAppointmentDTO dto) {
        var now = LocalDateTime.now();
        var appointmentDate = dto.appointment().getDate();

        var spanBetweenNowAndAppointmentDate = Duration.between(now, appointmentDate).toHours();

        if (spanBetweenNowAndAppointmentDate < 24) {
            throw new UnableToCancelAppointmentException(
                    "Cannot cancel an appointment less than 24 hours in advance "
            );
        }
    }
}
