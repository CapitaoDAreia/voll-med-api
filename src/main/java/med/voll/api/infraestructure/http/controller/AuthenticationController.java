package med.voll.api.infraestructure.http.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.application.services.TokenServices;
import med.voll.api.domain.dtos.authentication.AuthenticationDTO;
import med.voll.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager manager;

    @Autowired
    TokenServices tokenServices;

    @PostMapping
    public ResponseEntity<?> authenticate(@NotNull @Valid @RequestBody AuthenticationDTO dto){
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        Authentication authentication = this.manager.authenticate(usernamePasswordAuthenticationToken);

        User user = (User) authentication.getPrincipal();
        String token = tokenServices.generateToken(user);

        return ResponseEntity.ok().body(token);
    }
}
