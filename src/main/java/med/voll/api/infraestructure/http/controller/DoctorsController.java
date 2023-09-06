package med.voll.api.infraestructure.http.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dtos.DoctorsDTO;
import med.voll.api.domain.models.Doctor;
import med.voll.api.domain.repositories.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorsController {

    @Autowired
    DoctorsRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid DoctorsDTO dto) {
        Doctor doctor = new Doctor(dto);
        Doctor result = repository.save(doctor);
        System.out.println(result);
    }
}
