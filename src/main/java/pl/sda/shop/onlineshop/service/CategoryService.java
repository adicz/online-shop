package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow();
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findByName(String categoryName){
        return categoryRepository.findByName(categoryName);
    }

}
