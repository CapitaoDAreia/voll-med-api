package med.voll.api.infraestructure.http.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dtos.CreateDoctorsDTO;
import med.voll.api.domain.dtos.ListDoctorsDTO;
import med.voll.api.domain.dtos.UpdateDoctorsDTO;
import med.voll.api.domain.models.Doctor;
import med.voll.api.domain.repositories.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorsController {

    @Autowired
    DoctorsRepository repository;

    @PostMapping
    @Transactional
    public void createDoctors(@RequestBody @Valid CreateDoctorsDTO dto) {
        Doctor doctor = new Doctor(dto);
        Doctor result = repository.save(doctor);
        System.out.println(result);
    }

    @GetMapping
    public Page<ListDoctorsDTO> listDoctors(Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(ListDoctorsDTO::new);
    }

    @PutMapping
    @Transactional
    public void updateDoctors(@RequestBody @Valid UpdateDoctorsDTO dto) {
        Doctor doctor = repository.getReferenceById(dto.id());
        doctor.updateInfo(dto);
        System.out.println(dto);
    }

    @DeleteMapping(value = "/{ID}")
    @Transactional
    public void deleteDoctor(@PathVariable(value = "ID") Long ID) {
        Doctor doctor = repository.getReferenceById(ID);
        doctor.setInactive();
    }
}
