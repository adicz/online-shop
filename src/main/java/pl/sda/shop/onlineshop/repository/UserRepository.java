package pl.sda.shop.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shop.onlineshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameAndEmail(String username, String email);
}
