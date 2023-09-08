package med.voll.api.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.dtos.patients.CreatePatientsDTO;
import med.voll.api.domain.dtos.patients.UpdatePatientsDTO;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String cpf;

    @Embedded
    private Address address;

    private Boolean active;

    public Patient(@NotNull CreatePatientsDTO dto){
        this.active = true;
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.cpf = dto.cpf();
        this.address = new Address(dto.address());
    }

    public void updateInfo(UpdatePatientsDTO dto) {
        if(dto != null){
            if(dto.name() != null){
                this.name = dto.name();
            }
            if(dto.phone() != null){
                this.phone = dto.phone();
            }
            if(dto.address() != null){
                this.address.updateInfo(dto.address());
            }
        }
    }

    public void setInactive() {
        this.active = false;
    }
}
