package med.voll.api.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.dtos.CreatePatientsDTO;

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

    public Patient(@NotNull CreatePatientsDTO dto){
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.cpf = dto.cpf();
        this.address = new Address(dto.address());
    }

}
