package med.voll.api.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.enums.DoctorsExpertiseEnums;

public record CreateDoctorsDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotNull
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        DoctorsExpertiseEnums expertise,
        @NotNull
        AddressDTO address
) {}
