package med.voll.api.application.services;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.dtos.appointments.CancelAppointmentDTO;
import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.Patient;
import med.voll.api.domain.exceptions.EmptyExpertiseException;
import med.voll.api.domain.exceptions.UnableToCancelAppointmentException;
import med.voll.api.domain.repositories.AppointmentsRepository;
import med.voll.api.domain.repositories.DoctorsRepository;
import med.voll.api.domain.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AppointmentServices {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    public void toSchedule(ScheduleAppointmentsDTO dto) {
        if (!patientsRepository.existsById(dto.idPatient())) {
            throw new EntityNotFoundException("Patient id not found");
        }

        Patient patient = patientsRepository.getReferenceById(dto.idPatient());

        if (dto.idDoctor() != null && !doctorsRepository.existsById(dto.idDoctor())) {
            throw new EntityNotFoundException("Doctor id not found");
        }

        Doctor doctor = chooseDoctor(dto);

        Appointment appointment = new Appointment(null, doctor, patient, dto.date(), true);

        appointmentsRepository.save(appointment);
    }

    public void toCancel(CancelAppointmentDTO cancelAppointmentDTO) {
        if (cancelAppointmentDTO.appointment().getId() == null) {
            throw new IllegalArgumentException("Cannot cancel an appointment without appointment ID");
        }

        var spanBetweenNowAndAppointmentDate = calculateSpanBetweenNowAndAppointmentDate(cancelAppointmentDTO);

        if (spanBetweenNowAndAppointmentDate < 24) {
            throw new UnableToCancelAppointmentException(
                    "Cannot cancel an appointment less than 24 hours in advance "
            );
        }

        Appointment appointment = appointmentsRepository
                .getReferenceById(cancelAppointmentDTO.appointment().getId());

        appointment.setInactive();
    }

    private Doctor chooseDoctor(ScheduleAppointmentsDTO dto) {
        if (dto.idDoctor() != null) {
            return doctorsRepository.getReferenceById(dto.idDoctor());
        }

        if (dto.expertise() == null) {
            throw new EmptyExpertiseException("Doctor id not provided and expertise is null");
        }

        return doctorsRepository.chooseRandomActiveAndAvailableDoctor(dto.expertise(), dto.date());
    }

    private Long calculateSpanBetweenNowAndAppointmentDate(CancelAppointmentDTO cancelAppointmentDTO) {
        var now = LocalDateTime.now();
        var appointmentDate = cancelAppointmentDTO.appointment().getDate();

        return Duration.between(now, appointmentDate).toHours();
    }
}