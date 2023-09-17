package med.voll.api.domain.validations;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.exceptions.UnableToScheduleAppointmentException;
import med.voll.api.domain.repositories.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateIfPatientAlreadyHasAnAppointmentInThatDay implements ValidateBusinessRulesInterface{
    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Override
    public void validate(ScheduleAppointmentsDTO dto){
        //TODO: review this withHour method
        var firstHour = dto.date().withHour(7);
        var lastHour = dto.date().withHour(18);

        var isPatientAlreadyHasAnAppointmentInThisDay = appointmentsRepository
                .existsByPatientIdAndDateBetween(dto.idPatient(), firstHour, lastHour);

        if(isPatientAlreadyHasAnAppointmentInThisDay){
            throw new UnableToScheduleAppointmentException("Patient already have an appointment in this day");
        }
    }

}
