package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.shop.onlineshop.model.Category;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.model.ProductCount;
import pl.sda.shop.onlineshop.model.ShoppingCart;
import pl.sda.shop.onlineshop.repository.ShoppingCartRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    private final Long SHOPPING_CART_ID = 1L;
    private static final BigDecimal SHOPPING_CART_PRICE = BigDecimal.valueOf(150.00);

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

    private static final ProductCount PRODUCT_COUNT = new ProductCount(1L, PRODUCT, 1);
    private static final ProductCount PRODUCT_COUNT_2 = new ProductCount(2L, PRODUCT_2, 2);

    private static List<ProductCount> PRODUCTS_COUNTS = Arrays.asList(PRODUCT_COUNT, PRODUCT_COUNT_2);

    private static final ShoppingCart SHOPPING_CART = new ShoppingCart(1l, PRODUCTS_COUNTS, SHOPPING_CART_PRICE);

    @Mock
    ShoppingCartRepository shoppingCartRepository;
    @InjectMocks
    ShoppingCartService shoppingCartService;


    @Test
    void shouldReturnShoppingCartById() {
        //given
        Mockito.when(shoppingCartRepository.findById(any())).thenReturn(Optional.of(SHOPPING_CART));
        //then
        ShoppingCart result = shoppingCartService.findById(SHOPPING_CART_ID);
        //when
        assertEquals(SHOPPING_CART, result);
    }

    @Test
    void shouldSaveShoppingCart() {
        //given
        Mockito.when(shoppingCartRepository.save(any())).thenReturn(SHOPPING_CART);
        //then
        ShoppingCart result = shoppingCartService.save(SHOPPING_CART);
        //when
        assertEquals(SHOPPING_CART, result);
    }

    @Test
    void shouldDeleteShoppingCartById() {
        //then
        boolean result = shoppingCartService.deleteById(SHOPPING_CART_ID);
        //when
        assertTrue(result);
    }

}