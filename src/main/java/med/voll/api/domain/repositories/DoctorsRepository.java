package med.voll.api.domain.repositories;

import med.voll.api.domain.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<Doctor, Long> {}
