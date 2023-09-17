package med.voll.api.domain.validations;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;

public interface ValidateBusinessRulesInterface {
    void validate(ScheduleAppointmentsDTO dto);
}
