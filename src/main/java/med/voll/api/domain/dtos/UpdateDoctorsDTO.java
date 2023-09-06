package med.voll.api.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record UpdateDoctorsDTO(@NotNull Long id, String name, String phone, AddressDTO address) {}
