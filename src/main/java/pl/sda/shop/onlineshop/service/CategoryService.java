package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exception.category.CategoryAlreadyExist;
import pl.sda.shop.onlineshop.exception.category.CategoryNotFoundException;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        if (categoryRepository.existByName(category.getCategoryName()))
        {
            throw new CategoryAlreadyExist(String.format(
                    "Category with categoryName '%s' already exist in database", category.getCategoryName()));
        }
        return categoryRepository.save(category);
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(String.format("Category with id = %d not found in database", id)));
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findByName(String categoryName){
        return categoryRepository.findByName(categoryName);
    }

    public Category update(Category category)
    {
        if (categoryRepository.existsById(category.getId())){
            return categoryRepository.save(category);
        }
        throw new CategoryNotFoundException(String.format("Category with id = %d not found in the database", category.getId()));
    }

    public boolean deleteById(Category category){
        categoryRepository.deleteById(category.getId());
        return true;
    }

}
