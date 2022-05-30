package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.BeforeAll;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private final long PRODUCT_ID = 10L;

    private static final Product PRODUCT = new Product(
            1L,
            "shoes",
            "white running shoes",
            "https://images.unsplash.com/photo-1460353581641-37baddab0fa2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8c2hvZXN8ZW58MHx8MHx8&w=1000&q=80",
            100,
            new Category(),
            BigDecimal.valueOf(50.00),
            "NIKE");

    private static final Product PRODUCT_2 = new Product(
            2L,
            "shoes",
            "white training shoes",
            "https://images.unsplash.com/photo-1460353581641-37baddab0fa2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8c2hvZXN8ZW58MHx8MHx8&w=1000&q=80",
            100,
            new Category(),
            BigDecimal.valueOf(50.00),
            "NIKE");

    private static final List<Product> PRODUCTS = Arrays.asList(PRODUCT, PRODUCT_2);

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
        Product result = productService.addProduct(PRODUCT);
        //THEN
        assertEquals(PRODUCT, result);
    }
    @Test
    void shouldReturnAllProducts() {
        //GIVEN
        Mockito.when(productRepository.findAll()).thenReturn(PRODUCTS);
        //when
        List<Product> result = productService.findAll();
        //then
        assertEquals(PRODUCTS, result);
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

    @Test
    void shouldUpdateProduct() {

    }
}