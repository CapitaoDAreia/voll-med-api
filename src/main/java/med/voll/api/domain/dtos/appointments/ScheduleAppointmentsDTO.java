package med.voll.api.domain.dtos.appointments;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.domain.enums.DoctorsExpertiseEnums;

import java.time.LocalDateTime;

public record ScheduleAppointmentsDTO(
        Long idDoctor,
        @NotNull
        Long idPatient,
        @NotNull
        @Future
        LocalDateTime date,
        DoctorsExpertiseEnums expertise
) {}
