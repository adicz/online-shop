package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.shop.onlineshop.exception.category.CategoryNotFoundException;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.repository.CategoryRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    private static final Category CATEGORY = new Category(1L, "Computers", null);
    private static final Category CATEGORY_2 = new Category(2L, "Notebooks", null);
    private static final Category CATEGORY_2_UPDATED = new Category(2L, "PC", CATEGORY);
    private static final List<Category> CATEGORIES = Arrays.asList(CATEGORY, CATEGORY_2);
    private static final Long CATEGORY_ID = 3L;

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    void shouldSaveCategory() {
        //given
        Mockito.when(categoryRepository.save(any())).thenReturn(CATEGORY);
        //when
        Category result = categoryService.save(CATEGORY);
        //then
        assertEquals(CATEGORY, result);
    }

    @Test
    void shouldReturnCategoryById() {
        //given
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.of(CATEGORY));
        //when
        Category result = categoryService.findById(CATEGORY_ID);
        //then
        assertEquals(CATEGORY, result);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNotExist() {
        //given
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        //when & then
        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.findById(CATEGORY_ID));
    }

    @Test
    void shouldReturnAllCategories() {
        //given
        Mockito.when(categoryRepository.findAll()).thenReturn(CATEGORIES);
        //when
        List<Category> result = categoryService.findAll();
        //then
        assertEquals(CATEGORIES, result);
    }

    @Test
    void shouldReturnCategoryByName() {
        //given
        Mockito.when(categoryRepository.findCategoryByName(any())).thenReturn(Optional.of(CATEGORY));
        //when
        Category result = categoryService.findByName("Computers");
        //then
        assertEquals(CATEGORY, result);
    }

    @Test
    void shouldUpdateCategory() {
        //given
        Mockito.when(categoryRepository.existsById(any())).thenReturn(true);
        Mockito.when(categoryRepository.save(any())).thenReturn(CATEGORY_2_UPDATED);
        //then
        Category result = categoryService.update(CATEGORY_2_UPDATED);
        //when
        assertEquals(CATEGORY_2_UPDATED, result);
    }

    @Test
    void shouldDeleteCategoryById() {
        //then
        boolean result = categoryService.deleteById(CATEGORY_ID);
        //when
        assertTrue(result);
    }
}