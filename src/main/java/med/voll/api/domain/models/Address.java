package med.voll.api.domain.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.dtos.address.AddressDTO;

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

    public void updateInfo(AddressDTO dto) {
        if(dto != null){
            if(dto.street() != null){
                this.street = dto.street();
            }
            if(dto.neighborhood() != null){
                this.neighborhood = dto.neighborhood();
            }
            if(dto.postalCode() != null){
                this.postalCode = dto.postalCode();
            }
            if(dto.city() != null){
                this.city = dto.city();
            }
            if(dto.uf() != null){
                this.uf = dto.uf();
            }
            if(dto.number() != null){
                this.number = dto.number();
            }
            if(dto.complement() != null){
                this.complement = dto.complement();
            }
        }
    }
}
