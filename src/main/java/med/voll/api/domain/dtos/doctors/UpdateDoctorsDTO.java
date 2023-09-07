package med.voll.api.domain.dtos.doctors;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dtos.address.AddressDTO;

public record UpdateDoctorsDTO(@NotNull Long id, String name, String phone, AddressDTO address) {}
