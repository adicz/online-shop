package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.repository.ProductRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private final long PRODUCT_ID = 10L;

    private final Product PRODUCT = new Product(
            1L,
            "shoes",
            "white running shoes",
            "https://images.unsplash.com/photo-1460353581641-37baddab0fa2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8c2hvZXN8ZW58MHx8MHx8&w=1000&q=80",
            100,
            new Category(),
            50.00,
            "NIKE")
            ;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnProductById() {
        //GIVEN
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(PRODUCT));
        //WHEN
        Product result = productService.findById(PRODUCT_ID);
        //THEN
        assertEquals(PRODUCT, result);
    }

    @Test
    void shouldSaveProduct() {
        //GIVEN
        Mockito.when(productRepository.save(any())).thenReturn(PRODUCT);
        //WHEN
        Product result = productService.save(PRODUCT);
        //THEN
        assertEquals(PRODUCT, result);
    }
    @Test
    void shouldReturnAllProducts() {

    }

    @Test
    void shouldThrowExceptionIfProductDoesntExist() {
        //GIVEN
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.empty());
        //WHEN & THEN
        assertThrows(NoSuchElementException.class,
                () -> productService.findById(PRODUCT_ID),
                "Product with id = " +
                        PRODUCT_ID +
                        " not fount in database");
    }
}