package med.voll.api.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.dtos.DoctorsDTO;
import med.voll.api.domain.enums.DoctorsExpertiseEnums;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String crm;

    @Enumerated(EnumType.STRING)
    private DoctorsExpertiseEnums expertise;

    @Embedded
    private Address address;

    public Doctor(DoctorsDTO dto){
        this.name = dto.name();
        this.email = dto.email();
        this.crm = dto.crm();
        this.expertise = dto.expertise();
        this.address = new Address(dto.address());
    }
}
