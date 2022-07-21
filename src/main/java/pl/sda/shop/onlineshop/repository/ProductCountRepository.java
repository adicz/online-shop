package pl.sda.shop.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shop.onlineshop.model.ProductCount;

public interface ProductCountRepository extends JpaRepository<ProductCount, Long> {

}
