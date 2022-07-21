package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.shop.onlineshop.exception.shoppingCart.ShoppingCartNotFoundException;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.model.ProductCount;
import pl.sda.shop.onlineshop.model.ShoppingCart;
import pl.sda.shop.onlineshop.repository.ShoppingCartRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    private final Long SHOPPING_CART_ID = 1L;
    private static final BigDecimal SHOPPING_CART_PRICE = BigDecimal.valueOf(150.00);
    private static final Product PRODUCT = new Product();
    private static final Product PRODUCT_2 = new Product();
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
    void shouldThrowExceptionIfShoppingCartDoesntExist() {
        //given
        Mockito.when(shoppingCartRepository.findById(any())).thenReturn(Optional.empty());
        //then&when
        assertThrows(ShoppingCartNotFoundException.class,
                () -> shoppingCartService.findById(SHOPPING_CART_ID),
                "ShoppingCart with id = %d not found ");
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