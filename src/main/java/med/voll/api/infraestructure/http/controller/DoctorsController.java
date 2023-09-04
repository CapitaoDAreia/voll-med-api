package med.voll.api.infraestructure.http.controller;

import med.voll.api.domain.dtos.DoctorsDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorsController {
    @PostMapping
    public DoctorsDTO create(@RequestBody DoctorsDTO dto) {
        System.out.println(dto);
        return dto;
    }
}
