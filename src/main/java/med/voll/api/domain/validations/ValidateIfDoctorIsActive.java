package med.voll.api.domain.validations;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.exceptions.UnableToScheduleAppointmentException;
import med.voll.api.domain.repositories.DoctorsRepository;

public class ValidateIfDoctorIsActive {

    DoctorsRepository doctorsRepository;

    public void isDoctorActive(ScheduleAppointmentsDTO dto){
        var doctorId = dto.idDoctor();

        if(doctorId == null) return;

        Boolean isDoctorActive = doctorsRepository.findActiveById(doctorId);

        if(!isDoctorActive){
            throw new UnableToScheduleAppointmentException("Doctor is inactive");
        }
    }

}
