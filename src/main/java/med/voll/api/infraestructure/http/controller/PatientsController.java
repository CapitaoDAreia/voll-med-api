package med.voll.api.infraestructure.http.controller;

import med.voll.api.domain.dtos.PatientsDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientsController {
    @PostMapping
    public PatientsDTO create(@RequestBody PatientsDTO dto) {
        System.out.println(dto);
        return dto;
    }
}
