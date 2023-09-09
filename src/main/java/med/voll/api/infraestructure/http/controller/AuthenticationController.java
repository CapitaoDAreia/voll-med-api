package med.voll.api.infraestructure.http.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dtos.authentication.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<?> authenticate(@NotNull @Valid @RequestBody AuthenticationDTO dto){
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var authentication = this.manager.authenticate(token);

        return ResponseEntity.ok().body(authentication);
    }
}
