package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.repository.CategoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    private static final Category CATEGORY = new Category(1L, "Computers");

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;


    @Test
    void shouldAddCategory(){
        //given
        Mockito.when(categoryRepository.save(any())).thenReturn(CATEGORY);
        //when
        Category result = categoryService.addCategory(CATEGORY);
        //then
        assertEquals(CATEGORY, result);
    }



}