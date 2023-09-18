package med.voll.api.domain.validations.cancelAppointments;

import med.voll.api.domain.dtos.appointments.CancelAppointmentDTO;

public interface ValidateCancelAppointmentsBusinessRulesInterface {
    void validate(CancelAppointmentDTO dto);
}
