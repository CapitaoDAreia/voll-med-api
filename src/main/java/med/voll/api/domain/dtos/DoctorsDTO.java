package med.voll.api.domain.dtos;

import med.voll.api.domain.enums.DoctorsExpertiseEnums;

public record DoctorsDTO(String name, String email, String crm, DoctorsExpertiseEnums expertise, DoctorsAddressDTO address) {}
