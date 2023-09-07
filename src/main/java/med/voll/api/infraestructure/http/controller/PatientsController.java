package med.voll.api.infraestructure.http.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dtos.CreatePatientsDTO;
import med.voll.api.domain.dtos.ListPatientsDTO;
import med.voll.api.domain.dtos.UpdatePatientsDTO;
import med.voll.api.domain.models.Patient;
import med.voll.api.domain.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientsController {

    @Autowired
    PatientsRepository repository;

    @Transactional
    @PostMapping
    public void create(@RequestBody @Valid CreatePatientsDTO dto) {
        Patient patient = new Patient(dto);
        this.repository.save(patient);
        System.out.println(dto);
    }

    @GetMapping
    public Page<ListPatientsDTO> listPatients(Pageable pagination){
        return repository.findAllByActiveTrue(pagination).map(ListPatientsDTO::new);
    }

    @PutMapping
    @Transactional
    public void updatePatients(@RequestBody UpdatePatientsDTO dto){
        Patient patient = repository.getReferenceById(dto.id());
        patient.updateInfo(dto);
    }

    @DeleteMapping(value = "/{ID}")
    @Transactional
    public void deletePatients(@PathVariable(value = "ID") Long ID){
        Patient patient = repository.getReferenceById(ID);
        patient.setInactive();
    }
}
