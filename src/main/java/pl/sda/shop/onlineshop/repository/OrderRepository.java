package pl.sda.shop.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shop.onlineshop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
