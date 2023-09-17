package med.voll.api.domain.validations;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.exceptions.UnableToScheduleAppointmentException;
import med.voll.api.domain.repositories.PatientsRepository;

public class ValidateIfPatientIsActive {

    PatientsRepository patientsRepository;

    public void isPatientActive(ScheduleAppointmentsDTO dto){
        var patientId = dto.idPatient();

        if(patientId == null) throw new UnableToScheduleAppointmentException("Patient id is required");

        Boolean isPatientActive = patientsRepository.findActiveById(patientId);
        if(!isPatientActive){
            throw new UnableToScheduleAppointmentException("Patient is inactive");
        }
    }

}
