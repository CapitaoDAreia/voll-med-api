package med.voll.api.domain.dtos.appointments;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.domain.enums.CancelAppointmentReason;

public record CancelAppointmentDTO(
        @NotNull
        Appointment appointment,
        @NotNull
        CancelAppointmentReason reason
) {}
