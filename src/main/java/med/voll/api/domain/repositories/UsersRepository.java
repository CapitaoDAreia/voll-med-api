package med.voll.api.domain.repositories;

import med.voll.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}
