package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.repository.CategoryRepository;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    private static final Category CATEGORY = new Category(1L, "Computers", null);
    private static final Category CATEGORY2 = new Category(2L, "Notebooks", CATEGORY);

    List<Category> categories = Arrays.asList(CATEGORY, CATEGORY2);

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

    @Test
    void shouldReturnCategoryById(){
        //given
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.of(CATEGORY));
        //when
        Category result = categoryService.findById(1l);
        //then
        assertEquals(CATEGORY, result);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotExist(){
        //given
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        //when & then
        assertThrows(NoSuchElementException.class,
                () -> categoryService.findById(3L));
    }

    @Test
    void shouldReturnAllCategories(){
        //given
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
        //when
        List<Category> result = categoryService.findAll();
        //then
        assertEquals(categories, result);
    }

    @Test
    void shouldReturnCategoryByName(){
        //given
        Mockito.when(categoryRepository.findByName(any())).thenReturn(CATEGORY);
        //when
        Category result = categoryService.findByName("Computers");
        //then
        assertEquals(CATEGORY, result);
    }
}