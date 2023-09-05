package med.voll.api.domain.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.dtos.AddressDTO;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private String neighborhood;

    private String postalCode;
    private String city;
    private String uf;
    private String number;
    private String complement;

    public Address(AddressDTO dto){
        this.street = dto.street();
        this.neighborhood = dto.neighborhood();
        this.postalCode = dto.postalCode();
        this.city = dto.city();
        this.uf = dto.uf();
        this.number = dto.number();
        this.complement = dto.complement();
    }
}
