package med.voll.api.domain.dtos.patients;

import med.voll.api.domain.dtos.address.AddressDTO;

public record UpdatePatientsDTO(Long id, String name, String phone, AddressDTO address) {
}
