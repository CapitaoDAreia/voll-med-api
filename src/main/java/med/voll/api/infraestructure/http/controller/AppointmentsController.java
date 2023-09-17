package med.voll.api.infraestructure.http.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.application.services.AppointmentServices;
import med.voll.api.domain.dtos.appointments.CancelAppointmentDTO;
import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.dtos.appointments.ScheduledAppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/appointments")
public class AppointmentsController {

    @Autowired
    AppointmentServices appointmentServices;

    @PostMapping
    public ResponseEntity<?> toSchedule(@RequestBody @Valid ScheduleAppointmentsDTO scheduleAppointmentsDTO) {
        ScheduledAppointmentDTO scheduledAppointmentDTO = appointmentServices.toSchedule(scheduleAppointmentsDTO);
        return ResponseEntity.ok(scheduledAppointmentDTO);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> toCancel(@RequestBody @Valid CancelAppointmentDTO cancelAppointmentDTO) {
        appointmentServices.toCancel(cancelAppointmentDTO);
        return ResponseEntity.noContent().build();
    }
}
