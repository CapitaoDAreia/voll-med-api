package med.voll.api.domain.repositories;

import med.voll.api.domain.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByDoctorIdAndDate(Long aLong, LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstHour, LocalDateTime lastHour);
}
