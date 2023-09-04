package med.voll.api.infraestructure.http.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class Controller {

    @GetMapping
    public String helloWorld(){
        return "Hello world!";
    }
}
