package pl.sda.shop.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategory(Category category);
    List<Product> findProductsByBrand(String brand);
    List<Product> findProductsByTitle(String title);
}
