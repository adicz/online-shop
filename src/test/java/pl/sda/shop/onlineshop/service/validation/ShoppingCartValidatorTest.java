package pl.sda.shop.onlineshop.service.validation;

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
import pl.sda.shop.onlineshop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ShoppingCartValidatorTest {


    private static final Product PRODUCT = new Product(
            1L,
            "shoes",
            "white running shoes",
            null,
            100,
            new Category(),
            BigDecimal.valueOf(50.00),
            "NIKE");
    private static final Product PRODUCT_2 = new Product(
            2L,
            "shoes",
            "white running shoes",
            null,
            100,
            new Category(),
            BigDecimal.valueOf(100.00),
            "NIKE");
    private static final ProductCount PRODUCT_COUNT = new ProductCount(1L, PRODUCT, 1);
    private static final ProductCount PRODUCT_COUNT_2 = new ProductCount(2L, PRODUCT_2, 1);
    private static final BigDecimal SHOPPING_CART_PRICE = BigDecimal.valueOf(150.00);
    private static final BigDecimal SHOPPING_CART_PRICE_2 = BigDecimal.valueOf(70.00);
    private static List<ProductCount> PRODUCTS_COUNTS = Arrays.asList(PRODUCT_COUNT, PRODUCT_COUNT_2);

    private static final ShoppingCart SHOPPING_CART = new ShoppingCart(1L, PRODUCTS_COUNTS, SHOPPING_CART_PRICE);
    private static final ShoppingCart SHOPPING_CART_2 = new ShoppingCart(2L, PRODUCTS_COUNTS, SHOPPING_CART_PRICE_2);


    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ShoppingCartValidator shoppingCartValidator;

    @Test
    void shouldReturnTrueIfPriceIsCorrect() {
        //given
        Mockito.when(shoppingCartValidator.isValid(SHOPPING_CART));
        //when
        boolean result = shoppingCartValidator.isValid(SHOPPING_CART);
        //then
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfPriceIsNotCorrect() {
        //given
        Mockito.when(shoppingCartValidator.isValid(SHOPPING_CART_2));
        //when
        boolean result = shoppingCartValidator.isValid(SHOPPING_CART_2);
        //then
        assertFalse(result);
    }


}