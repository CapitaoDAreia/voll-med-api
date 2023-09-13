package med.voll.api.domain.dtos.appointments;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

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
) {}
