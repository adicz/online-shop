package pl.sda.shop.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shop.onlineshop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<List<Order>> findOrdersByUserUsername(String username);
}
