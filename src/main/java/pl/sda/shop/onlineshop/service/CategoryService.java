package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exception.category.CategoryAlreadyExistsException;
import pl.sda.shop.onlineshop.exception.category.CategoryNotFoundException;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        if (categoryRepository.existsCategoryByName(category.getName())) {
            throw new CategoryAlreadyExistsException(category.getName());
        }
        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findByName(String name) {
        return categoryRepository.findCategoryByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(name));
    }

    public Category update(Category category) {
        findById(category.getId());
        return categoryRepository.save(category);
    }

    public boolean deleteById(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }

}
