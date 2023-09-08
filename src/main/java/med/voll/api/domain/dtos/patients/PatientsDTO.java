package med.voll.api.domain.dtos.patients;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.entities.Address;
import med.voll.api.domain.entities.Patient;

public record PatientsDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String phone,
        @NotBlank
        String cpf,
        @NotNull
        Address address
) {
    public PatientsDTO(@NotNull Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress());
    }
}
