package med.voll.api.domain.repositories;

import med.voll.api.domain.dtos.address.AddressDTO;
import med.voll.api.domain.dtos.doctors.CreateDoctorsDTO;
import med.voll.api.domain.dtos.patients.CreatePatientsDTO;
import med.voll.api.domain.entities.Appointment;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.Patient;
import med.voll.api.domain.enums.DoctorsExpertiseEnums;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(profiles = "-test")
class DoctorsRepositoryTest {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Should return null when registered doctor is unavailable in this date")
    void chooseRandomActiveAndAvailableDoctorScenario1() {

        var nextMonday10Am = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);


        var freeDoctor = doctorsRepository.chooseRandomActiveAndAvailableDoctor(DoctorsExpertiseEnums.CARDIOLOGIA, nextMonday10Am);

        Assertions.assertThat(freeDoctor).isNull();
    }



    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date, true));
    }

    private Doctor registerDoctor(String name, String email, String crm, DoctorsExpertiseEnums expertise) {
        var doctor = new Doctor(createDoctorsDTO(name, email, crm, expertise));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(patientsDTO(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private CreateDoctorsDTO createDoctorsDTO(String name, String email, String crm, DoctorsExpertiseEnums expertise) {
        return new CreateDoctorsDTO(
                name,
                email,
                "",
                crm,
                expertise,
                addressDTO()
        );
    }

    private CreatePatientsDTO patientsDTO(String name, String email, String cpf) {
        return new CreatePatientsDTO(
                name,
                email,
                "",
                cpf,
                addressDTO()
        );
    }

    private AddressDTO addressDTO() {
        return new AddressDTO(
                "rua tal",
                "0",
                "",
                "",
                "",
                null,
                null
        );
    }
}