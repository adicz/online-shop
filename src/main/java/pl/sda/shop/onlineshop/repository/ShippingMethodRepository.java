package pl.sda.shop.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shop.onlineshop.model.ShippingMethod;

public interface ShippingMethodRepository extends JpaRepository<ShippingMethod, Long> {

}
