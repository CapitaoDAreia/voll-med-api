package med.voll.api.domain.repositories;

import med.voll.api.domain.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<Patient, Long> {}
