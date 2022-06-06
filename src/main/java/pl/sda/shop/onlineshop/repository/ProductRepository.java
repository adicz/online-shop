package pl.sda.shop.onlineshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategory(Category category);

    Page<Product> findProductsByCategoryName(String categoryName, Pageable pageable);

    List<Product> findProductsByBrand(String brand);

    List<Product> findProductsByTitle(String title);

    @Query(value = "SELECT p " +
            "FROM Product p " +
            "WHERE (:category IS NULL OR p.category.name = :category) " +
            "AND (:brand IS NULL OR p.brand = :brand) " +
            "AND (:title IS NULL OR p.title LIKE %:title%)")
    Page<Product> findProductsByCategoryNameAndBrandNameAndTitle(@Param("category") String category, @Param("brand") String brand, @Param("title") String title, Pageable pageable);
}
