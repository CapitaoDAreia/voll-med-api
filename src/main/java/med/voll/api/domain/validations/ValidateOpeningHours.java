package med.voll.api.domain.validations;

import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.exceptions.UnableToScheduleAppointmentException;

import java.time.DayOfWeek;

public class ValidateOpeningHours {

    public void isInsideOpeningHour(ScheduleAppointmentsDTO dto) {
        var appointmentDate = dto.date();

        var isSunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isHourMinorThanSeven = appointmentDate.getHour() < 7;
        var isHourMajorThanEighteen = appointmentDate.getHour() > 18;

        if (isSunday || isHourMajorThanEighteen || isHourMinorThanSeven) {
            throw new UnableToScheduleAppointmentException("Check the opening hour");
        }
    }

}
