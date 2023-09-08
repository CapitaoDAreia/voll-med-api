package med.voll.api.domain.dtos.doctors;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.entities.Doctor;

public record ListDoctorsDTO(
        @NotNull Long id,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String crm,
        @NotBlank String expertise


) {
    public ListDoctorsDTO(@NotNull Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getExpertise().toString());
    }
}
