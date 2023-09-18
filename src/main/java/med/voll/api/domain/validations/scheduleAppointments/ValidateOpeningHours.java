package med.voll.api.domain.validations.scheduleAppointments;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.exceptions.UnableToScheduleAppointmentException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidateOpeningHours implements ValidateBusinessRulesInterface{
    @Override
    public void validate(ScheduleAppointmentsDTO dto) {
        var appointmentDate = dto.date();

        var isSunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isHourMinorThanSeven = appointmentDate.getHour() < 7;
        var isHourMajorThanEighteen = appointmentDate.getHour() > 18;

        if (isSunday || isHourMajorThanEighteen || isHourMinorThanSeven) {
            throw new UnableToScheduleAppointmentException("Check the opening hour");
        }
    }

}
