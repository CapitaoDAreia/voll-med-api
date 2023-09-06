package med.voll.api.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.models.Doctor;

public record ListDoctorsDTO(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String crm,
        @NotBlank String expertise


) {
    public ListDoctorsDTO(@NotNull Doctor doctor){
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getExpertise().toString());
    }
}
