package med.voll.api.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreatePatientsDTO(
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
        AddressDTO address
) {}
