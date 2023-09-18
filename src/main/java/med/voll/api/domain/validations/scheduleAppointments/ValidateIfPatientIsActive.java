package med.voll.api.domain.validations.scheduleAppointments;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.exceptions.UnableToScheduleAppointmentException;
import med.voll.api.domain.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateIfPatientIsActive implements ValidateBusinessRulesInterface{
    @Autowired
    PatientsRepository patientsRepository;

    @Override
    public void validate(ScheduleAppointmentsDTO dto){
        var patientId = dto.idPatient();

        if(patientId == null) throw new UnableToScheduleAppointmentException("Patient id is required");

        Boolean isPatientActive = patientsRepository.findActiveById(patientId);
        if(!isPatientActive){
            throw new UnableToScheduleAppointmentException("Patient is inactive");
        }
    }

}
