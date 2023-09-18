package med.voll.api.domain.validations.scheduleAppointments;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.exceptions.UnableToScheduleAppointmentException;
import med.voll.api.domain.repositories.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateIfDoctorIsActive implements ValidateBusinessRulesInterface{
    @Autowired
    DoctorsRepository doctorsRepository;

    @Override
    public void validate(ScheduleAppointmentsDTO dto){
        var doctorId = dto.idDoctor();

        if(doctorId == null) return;

        Boolean isDoctorActive = doctorsRepository.findActiveById(doctorId);

        if(!isDoctorActive){
            throw new UnableToScheduleAppointmentException("Doctor is inactive");
        }
    }

}
