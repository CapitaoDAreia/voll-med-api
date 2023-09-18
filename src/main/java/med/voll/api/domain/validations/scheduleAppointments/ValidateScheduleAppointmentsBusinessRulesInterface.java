package med.voll.api.domain.validations.scheduleAppointments;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;

public interface ValidateScheduleAppointmentsBusinessRulesInterface {
    void validate(ScheduleAppointmentsDTO dto);
}
