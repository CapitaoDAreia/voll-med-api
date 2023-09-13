package med.voll.api.application.services;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.dtos.appointments.ScheduleAppointmentsDTO;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.Patient;
import med.voll.api.domain.repositories.AppointmentsRepository;
import med.voll.api.domain.repositories.DoctorsRepository;
import med.voll.api.domain.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServices {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    public void toSchedule(ScheduleAppointmentsDTO dto) {
        Patient patient = null;
        Doctor doctor = null;

        try {
            patient = patientsRepository.findById(dto.idPatient()).get();
            doctor = doctorsRepository.findById(dto.idDoctor()).get();
        } catch (Exception ex) {
            throw new EntityNotFoundException("patient or doctor not found for this schedule");
        }

        Appointment appointment = new Appointment(null, doctor, patient, dto.date());

        appointmentsRepository.save(appointment);
    }
}
