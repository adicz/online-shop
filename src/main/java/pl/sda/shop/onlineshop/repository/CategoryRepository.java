package pl.sda.shop.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.shop.onlineshop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String categoryName);
    boolean existByName(String name);
}
