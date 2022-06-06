package pl.sda.shop.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.User;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findAddressByUser(User user);
    boolean existsAddressByUser(User user);
}
