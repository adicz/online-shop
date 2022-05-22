package pl.sda.shop.onlineshop.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.shop.onlineshop.exception.order.OrderNotFoundException;
import pl.sda.shop.onlineshop.exception.shoppingCart.ShoppingCartNotFoundException;
import pl.sda.shop.onlineshop.model.*;
import pl.sda.shop.onlineshop.model.enumerated.DeliveryMethod;
import pl.sda.shop.onlineshop.model.enumerated.OrderStatus;
import pl.sda.shop.onlineshop.model.enumerated.PaymentMethod;
import pl.sda.shop.onlineshop.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private static final Long ORDER_ID = 1l;
    private static final BigDecimal SHOPPING_CART_PRICE = BigDecimal.valueOf(50.00);
    private static final Product PRODUCT = new Product();
    private static final ProductCount PRODUCT_COUNT = new ProductCount(1L, PRODUCT, 1);
    private static List<ProductCount> PRODUCTS_COUNTS = Arrays.asList(PRODUCT_COUNT);
    private static final ShoppingCart SHOPPING_CART = new ShoppingCart(1l, PRODUCTS_COUNTS, SHOPPING_CART_PRICE);
    private static final Address ADDRESS = new Address();
    private static final User USER = new User();
    private static final Order ORDER = new Order(ORDER_ID, OrderStatus.ORDERED, DeliveryMethod.INPOST, PaymentMethod.BLIK, ADDRESS, SHOPPING_CART, USER);
    private static final List<Order> ORDERS = List.of(ORDER);

    @Mock
    OrderRepository orderRepository;
    @InjectMocks
    OrderService orderService;

    @Test
    void shouldReturnOrderById() {
        //given
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(ORDER));
        //then
        Order result = orderService.findById(ORDER_ID);
        //when
        assertEquals(ORDER, result);
    }

    @Test
    void shouldThrowExceptionIfOrderDoesntExist(){
        //given
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.empty());
        //then&when
        assertThrows(OrderNotFoundException.class,
                () -> orderService.findById(ORDER_ID),
                "Order with id = %d not found ");
    }

    @Test
    void shouldReturnAllOrders() {
        //given
        Mockito.when(orderRepository.findAll()).thenReturn(ORDERS);
        //then
        List<Order> result = orderService.findAll();
        //when
        assertEquals(ORDERS, result);
    }

    @Test
    void shouldSaveOrder() {
        //given
        Mockito.when(orderRepository.save(any())).thenReturn(ORDER);
        //then
        Order result = orderService.save(ORDER);
        //when
        assertEquals(ORDER, result);
    }

    @Test
    void shouldDeleteOrderById() {
        //then
        boolean result = orderService.deleteById(ORDER_ID);
        //when
        assertTrue(result);
    }

}