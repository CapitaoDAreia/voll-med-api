package med.voll.api.domain.dtos.patients;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.models.Address;
import med.voll.api.domain.models.Patient;

public record CreatedPatientsDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        Address address
) {
        public CreatedPatientsDTO(Patient patient){
                this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress());
        }
}
