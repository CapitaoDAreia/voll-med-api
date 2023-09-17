package med.voll.api.domain.repositories;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.enums.DoctorsExpertiseEnums;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorsRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pagination);

    @Query("""
                select d from Doctor d
                where
                d.active = true
                and
                d.expertise = :expertise
                and
                d.id not in(
                    select ap.doctor.id from Appointment ap
                    where
                    ap.date = :date
                )
                order by rand()
                limit 1
            """)
    Doctor chooseRandomActiveAndAvailableDoctor(DoctorsExpertiseEnums expertise, LocalDateTime date);

    @Query("""
            select d.active
            from Doctor d
            where
            d.id = :id
            """)
    Boolean findActiveById(Long id);
}
