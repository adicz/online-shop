package pl.sda.shop.onlineshop.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sda.shop.onlineshop.exception.order.OrderNotFoundException;
import pl.sda.shop.onlineshop.model.Address;
import pl.sda.shop.onlineshop.model.Order;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.model.ProductCount;
import pl.sda.shop.onlineshop.model.ShippingMethod;
import pl.sda.shop.onlineshop.model.ShoppingCart;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.model.enumerated.OrderStatus;
import pl.sda.shop.onlineshop.model.enumerated.PaymentMethod;
import pl.sda.shop.onlineshop.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    private static final ShippingMethod SHIPPING_METHOD = new ShippingMethod(1L, "DHL", BigDecimal.valueOf(10.00));
    private static final BigDecimal ORDER_PRICE = SHOPPING_CART_PRICE.add(SHIPPING_METHOD.getPrice());
    private static LocalDateTime localDateTime;
    private static final Order ORDER = new Order(ORDER_ID, localDateTime, OrderStatus.ORDERED, SHIPPING_METHOD, PaymentMethod.BLIK, ADDRESS, SHOPPING_CART, USER, ORDER_PRICE);
    private static final List<Order> ORDERS = List.of(ORDER);

    private static String USERNAME = "Johhny";

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
    void shouldThrowExceptionIfOrderDoesntExist() {
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
    void shouldReturnAllOrdersByUser() {
        //given
        Mockito.when(orderRepository.findOrdersByUserUsername(any())).thenReturn(Optional.of(ORDERS));
        //then
        List<Order> result = orderService.findAllByUser(USERNAME);
        //when
        assertEquals(ORDERS, result);
    }

    @Test
    void shouldSaveOrder() {
        //given
        Mockito.when(orderRepository.save(any())).thenReturn(ORDER);
        //then
        Order result = orderService.save(ORDER, USERNAME);
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