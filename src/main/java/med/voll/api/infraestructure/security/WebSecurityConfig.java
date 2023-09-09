package med.voll.api.infraestructure.security;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf().disable() //disable cross site request forgery protection policy
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //defines session management type
                .and().build(); //build the config
    }
}
