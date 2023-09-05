package med.voll.api.domain.dtos;

public record PatientsDTO(String name, String email, String phone, String CPF, AddressDTO address) {}
