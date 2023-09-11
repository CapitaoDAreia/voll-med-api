package med.voll.api.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.entities.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServices {
    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256("temp_secret_key");
            Instant expireDate = generateExpireDate();

            return JWT.create()
                    .withIssuer("Voll API")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("token creation failed: " + exception);
        }
    }

    private Instant generateExpireDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
