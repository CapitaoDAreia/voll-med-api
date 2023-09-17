package med.voll.api.domain.validations;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.exceptions.UnableToScheduleAppointmentException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidateAppointmentAdvance {

    public void isAppointmentAdvanceHourValid(ScheduleAppointmentsDTO dto) {
        var appointmentDate = dto.date();
        var now = LocalDateTime.now();

        var spanBetweenNowAndAppointment = Duration.between(now, appointmentDate).toMinutes();

        if (spanBetweenNowAndAppointment < 30) {
            throw new UnableToScheduleAppointmentException("Appointments can be scheduled with 30 min in advance");
        }
    }

}
