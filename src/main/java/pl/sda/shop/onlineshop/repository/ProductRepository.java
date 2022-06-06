package pl.sda.shop.onlineshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategory(Category category);
    Page<Product> findProductsByCategoryName(String categoryName, Pageable pageable);
    List<Product> findProductsByBrand(String brand);
    List<Product> findProductsByTitle(String title);
}
