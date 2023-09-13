package med.voll.api.infraestructure.http.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.dtos.appointments.ScheduledAppointmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/appointments")
public class AppointmentsController {

    @PostMapping
    public ResponseEntity<?> toSchedule(@RequestBody @Valid ScheduleAppointmentsDTO scheduleAppointmentsDTO) {

        System.out.println(new ScheduledAppointmentDTO(
                1L,
                scheduleAppointmentsDTO.idDoctor(),
                scheduleAppointmentsDTO.idPatient(),
                scheduleAppointmentsDTO.date()
        ));

        return ResponseEntity.ok().build();
    }
}
