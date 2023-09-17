package med.voll.api.domain.validations;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.exceptions.UnableToScheduleAppointmentException;
import med.voll.api.domain.repositories.AppointmentsRepository;

public class ValidateIfDoctorWillBeBusy {

    AppointmentsRepository appointmentsRepository;

    public void isDoctorBusyInThisHour(ScheduleAppointmentsDTO dto){
        var isDoctorBusyInThisHour = appointmentsRepository.existsByDoctorIdAndDate(dto.idDoctor(), dto.date());
        if(isDoctorBusyInThisHour){
            throw new UnableToScheduleAppointmentException("Doctor will be busy in this hour");
        }
    }

}
