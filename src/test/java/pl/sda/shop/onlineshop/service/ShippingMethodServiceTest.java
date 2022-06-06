package pl.sda.shop.onlineshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.shop.onlineshop.exception.shippingMethod.ShippingMethodNotFoundException;
import pl.sda.shop.onlineshop.model.ShippingMethod;
import pl.sda.shop.onlineshop.repository.ShippingMethodRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ShippingMethodServiceTest {

    private final Long SHIPPING_ID = 1L;
    private static final ShippingMethod SHIPPING_METHOD = new ShippingMethod(1l, "DHL", BigDecimal.valueOf(10.00));
    private static final ShippingMethod SHIPPING_METHOD_2 = new ShippingMethod(2l, "InPost", BigDecimal.valueOf(8.00));
    private static final List<ShippingMethod> SHIPPING_METHODS = List.of(SHIPPING_METHOD, SHIPPING_METHOD_2);

    @Mock
    ShippingMethodRepository shippingMethodRepository;

    @InjectMocks
    ShippingMethodService shippingMethodService;

    @Test
    void shouldReturnShippingMethodById() {
        //given
        Mockito.when(shippingMethodRepository.findById(any())).thenReturn(Optional.of(SHIPPING_METHOD));
        //then
        ShippingMethod result = shippingMethodService.findById(SHIPPING_ID);
        //when
        assertEquals(SHIPPING_METHOD, result);
    }

    @Test
    void shouldThrowExceptionIfShippingMethodDoesntExist() {
        //given
        Mockito.when(shippingMethodRepository.findById(any())).thenReturn(Optional.empty());
        //then&when
        assertThrows(ShippingMethodNotFoundException.class,
                () -> shippingMethodService.findById(SHIPPING_ID),
                "Shipping method with id = %d not found ");
    }

    @Test
    void shouldReturnAllShippingMethods() {
        //given
        Mockito.when(shippingMethodRepository.findAll()).thenReturn(SHIPPING_METHODS);
        //then
        List<ShippingMethod> result = shippingMethodService.findAll();
        //when
        assertEquals(SHIPPING_METHODS, result);
    }

    @Test
    void shouldSaveShippingMethod() {
        //given
        Mockito.when(shippingMethodRepository.save(any())).thenReturn(SHIPPING_METHOD);
        //then
        ShippingMethod result = shippingMethodService.save(SHIPPING_METHOD);
        //when
        assertEquals(SHIPPING_METHOD, result);
    }

    @Test
    void shouldDeleteShoppingMethodById() {
        //then
        boolean result = shippingMethodService.deleteById(SHIPPING_ID);
        //when
        assertTrue(result);
    }

}