package med.voll.api.domain.dtos.doctors;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.models.Address;
import med.voll.api.domain.models.Doctor;

public record CreatedDoctorsDTO(
        @NotNull Long id,
        String name,
        String email,
        String crm,
        String phone,
        String expertise,
        Address address
) {
    public CreatedDoctorsDTO(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getPhone(),
                doctor.getExpertise().toString(),
                doctor.getAddress());
    }
}
