package pl.sda.shop.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shop.onlineshop.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
}
