package pl.sda.shop.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shop.onlineshop.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
