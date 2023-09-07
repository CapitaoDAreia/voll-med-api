package med.voll.api.domain.dtos;

public record UpdatePatientsDTO(Long id, String name, String phone, AddressDTO address) {
}
