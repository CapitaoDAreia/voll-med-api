package med.voll.api.infraestructure.http.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dtos.patients.*;
import med.voll.api.domain.models.Patient;
import med.voll.api.domain.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientsController {

    @Autowired
    PatientsRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity<CreatedPatientsDTO> create(@RequestBody @Valid CreatePatientsDTO dto, UriComponentsBuilder uriBuilder) {
        Patient patient = new Patient(dto);
        Patient result = this.repository.save(patient);

        URI uri = uriBuilder.path("/patients/{ID}").buildAndExpand(result.getId()).toUri();

        CreatedPatientsDTO createdPatientsDTO = new CreatedPatientsDTO(result);

        return ResponseEntity.created(uri).body(createdPatientsDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ListPatientsDTO>> listPatients(Pageable pagination){
        Page<ListPatientsDTO> result = repository.findAllByActiveTrue(pagination).map(ListPatientsDTO::new);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UpdatedPatientsDTO> updatePatients(@RequestBody UpdatePatientsDTO dto){
        Patient patient = repository.getReferenceById(dto.id());
        patient.updateInfo(dto);

        return ResponseEntity.ok(new UpdatedPatientsDTO(patient));
    }

    @DeleteMapping(value = "/{ID}")
    @Transactional
    public ResponseEntity<?> deletePatients(@PathVariable(value = "ID") Long ID){
        Patient patient = repository.getReferenceById(ID);
        patient.setInactive();

        return ResponseEntity.noContent().build();
    }
}
