package med.voll.api.domain.repositories;

import med.voll.api.domain.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {}
