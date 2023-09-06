package med.voll.api.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.dtos.CreateDoctorsDTO;
import med.voll.api.domain.dtos.UpdateDoctorsDTO;
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

    private String phone;

    private String crm;

    @Enumerated(EnumType.STRING)
    private DoctorsExpertiseEnums expertise;

    @Embedded
    private Address address;

    private Boolean active;

    public Doctor(@NotNull CreateDoctorsDTO dto) {
        this.active = true;
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.crm = dto.crm();
        this.expertise = dto.expertise();
        this.address = new Address(dto.address());
    }

    public void updateInfo(UpdateDoctorsDTO dto) {
        if (dto != null) {
            if (dto.name() != null) {
                this.name = dto.name();
            }

            if (dto.phone() != null) {
                this.email = dto.phone();
            }

            if (dto.address() != null) {
                this.address.updateInfo(dto.address());
            }
        }
    }

    public void setInactive() {
        this.active = false;
    }
}
