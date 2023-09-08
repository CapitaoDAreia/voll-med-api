package med.voll.api.infraestructure.http.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dtos.doctors.*;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.repositories.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorsController {

    @Autowired
    DoctorsRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<CreatedDoctorsDTO> createDoctors(@RequestBody @Valid CreateDoctorsDTO dto, UriComponentsBuilder uriBuilder) {
        Doctor doctor = new Doctor(dto);
        Doctor result = repository.save(doctor);

        URI uri = uriBuilder.path("/doctors/{ID}").buildAndExpand(result.getId()).toUri();

        CreatedDoctorsDTO createdDoctorsDTO = new CreatedDoctorsDTO(result);

        return ResponseEntity.created(uri).body(createdDoctorsDTO);
    }

    @GetMapping(value = "/{ID}")
    public ResponseEntity<DoctorsDTO> getDoctor(@PathVariable(value = "ID") Long ID) {
        Doctor doctor = repository.getReferenceById(ID);
        DoctorsDTO dto = new DoctorsDTO(doctor);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ListDoctorsDTO>> listDoctors(Pageable pagination) {
        Page<ListDoctorsDTO> result = repository.findAllByActiveTrue(pagination).map(ListDoctorsDTO::new);

        return ResponseEntity.ok(result);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UpdatedDoctorsDTO> updateDoctors(@RequestBody @Valid UpdateDoctorsDTO dto) {
        Doctor doctor = repository.getReferenceById(dto.id());
        doctor.updateInfo(dto);

        return ResponseEntity.ok().body(new UpdatedDoctorsDTO(doctor));
    }

    @DeleteMapping(value = "/{ID}")
    @Transactional
    public ResponseEntity<?> deleteDoctor(@PathVariable(value = "ID") Long ID) {
        Doctor doctor = repository.getReferenceById(ID);
        doctor.setInactive();

        return ResponseEntity.noContent().build();
    }
}
