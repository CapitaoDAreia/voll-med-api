package med.voll.api.domain.validations.scheduleAppointments;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.exceptions.UnableToScheduleAppointmentException;
import med.voll.api.domain.repositories.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateIfDoctorWillBeBusy implements ValidateScheduleAppointmentsBusinessRulesInterface {
    @Autowired
    AppointmentsRepository appointmentsRepository;

    @Override
    public void validate(ScheduleAppointmentsDTO dto){
        var isDoctorBusyInThisHour = appointmentsRepository.existsByDoctorIdAndDate(dto.idDoctor(), dto.date());
        if(isDoctorBusyInThisHour){
            throw new UnableToScheduleAppointmentException("Doctor will be busy in this hour");
        }
    }

}
