package med.voll.api.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.models.Patient;

public record ListPatientsDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String cpf
) {
    public ListPatientsDTO(@NotNull Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
