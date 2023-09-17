package med.voll.api.domain.repositories;

import med.voll.api.domain.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientsRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByActiveTrue(Pageable pagination);

    @Query("""
            select p.active
            from Patient p
            where
            p.id = :id
            """)
    Boolean findActiveById(Long id);
}
