package med.voll.api.domain.dtos.authentication;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank
        String login,
        @NotBlank
        String password) {
}
