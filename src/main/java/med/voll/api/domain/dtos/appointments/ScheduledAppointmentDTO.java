package med.voll.api.domain.dtos.appointments;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.entities.Appointment;

import java.time.LocalDateTime;

public record ScheduledAppointmentDTO(
        @NotNull
        Long id,
        @NotNull
        Long idDoctor,
        @NotNull
        Long idPatient,
        @NotNull
        @Future
        LocalDateTime date
) {
    public ScheduledAppointmentDTO(Appointment appointment) {
        this(
                appointment.getId(),
                appointment.getDoctor().getId(),
                appointment.getPatient().getId(),
                appointment.getDate()
        );
    }
}
