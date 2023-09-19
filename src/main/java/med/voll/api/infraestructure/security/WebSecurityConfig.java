package med.voll.api.infraestructure.security;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable() //disable cross site request forgery protection policy
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //defines session management type
                .and().authorizeHttpRequests() //allows http request to be done
                .requestMatchers(HttpMethod.POST, "/api/v1/auth").permitAll() //defines which request are allowed
                .requestMatchers(HttpMethod.GET, "/v3/api-docs/**","/swagger-ui.html", "swagger-ui/**").permitAll() //defines which request are allowed
                .anyRequest().authenticated() //defines that all requests, except allowed previously, are permitted only when authenticated
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) //defines that our custom filter comes before Spring filter
                .build(); //build the config
    }

    @Bean
    public AuthenticationManager authenticationManager(@NotNull AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
