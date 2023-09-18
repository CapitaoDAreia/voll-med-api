package med.voll.api.domain.validations.scheduleAppointments;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;

public interface ValidateBusinessRulesInterface {
    void validate(ScheduleAppointmentsDTO dto);
}
