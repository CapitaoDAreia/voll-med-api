package med.voll.api.domain.validations;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.exceptions.UnableToScheduleAppointmentException;
import med.voll.api.domain.repositories.AppointmentsRepository;

public class ValidateIfPatientAlreadyHasAnAppointmentInThatDay {

    private AppointmentsRepository appointmentsRepository;

    public void isPatientAlreadyHasAnAppointmentInThisDay(ScheduleAppointmentsDTO dto){
        //TODO: review this withHour method
        var firstHour = dto.date().withHour(7);
        var lastHour = dto.date().withHour(18);

        var isPatientAlreadyHasAnAppointmentInThisDay = appointmentsRepository
                .existsByPatientAndDateBetween(dto.idPatient(), firstHour, lastHour);

        if(isPatientAlreadyHasAnAppointmentInThisDay){
            throw new UnableToScheduleAppointmentException("Patient already have an appointment in this day");
        }
    }

}
