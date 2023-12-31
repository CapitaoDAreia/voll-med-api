package med.voll.api.application.services;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.dtos.appointments.CancelAppointmentDTO;
import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.dtos.appointments.ScheduledAppointmentDTO;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.Patient;
import med.voll.api.domain.exceptions.EmptyExpertiseException;
import med.voll.api.domain.repositories.AppointmentsRepository;
import med.voll.api.domain.repositories.DoctorsRepository;
import med.voll.api.domain.repositories.PatientsRepository;
import med.voll.api.domain.validations.cancelAppointments.ValidateCancelAppointmentsBusinessRulesInterface;
import med.voll.api.domain.validations.scheduleAppointments.ValidateScheduleAppointmentsBusinessRulesInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServices {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private List<ValidateScheduleAppointmentsBusinessRulesInterface> scheduleAppointmentsValidators;
    @Autowired
    private List<ValidateCancelAppointmentsBusinessRulesInterface> cancelAppointmentsValidators;


    public ScheduledAppointmentDTO toSchedule(ScheduleAppointmentsDTO dto) {
        if (!patientsRepository.existsById(dto.idPatient())) {
            throw new EntityNotFoundException("Patient id not found");
        }

        if (dto.idDoctor() != null && !doctorsRepository.existsById(dto.idDoctor())) {
            throw new EntityNotFoundException("Doctor id not found");
        }

        scheduleAppointmentsValidators.forEach(rule -> rule.validate(dto));

        Patient patient = patientsRepository.getReferenceById(dto.idPatient());

        Doctor doctor = chooseDoctor(dto);

        if(doctor == null){
            throw new EntityNotFoundException("There is no doctors available in this date");
        }

        Appointment appointment = new Appointment(null, doctor, patient, dto.date(), true);

        appointmentsRepository.save(appointment);

        return new ScheduledAppointmentDTO(appointment);
    }

    public void toCancel(CancelAppointmentDTO dto) {
        if (dto.appointment().getId() == null) {
            throw new IllegalArgumentException("Cannot cancel an appointment without appointment ID");
        }

        cancelAppointmentsValidators.forEach(rule -> rule.validate(dto));

        Appointment appointment = appointmentsRepository
                .getReferenceById(dto.appointment().getId());

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
}