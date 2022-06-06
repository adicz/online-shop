package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exception.order.OrderNotFoundException;
import pl.sda.shop.onlineshop.exception.product.ProductNotAvailable;
import pl.sda.shop.onlineshop.exception.shoppingCart.PriceNotValidException;
import pl.sda.shop.onlineshop.model.Order;
import pl.sda.shop.onlineshop.model.User;
import pl.sda.shop.onlineshop.model.enumerated.OrderStatus;
import pl.sda.shop.onlineshop.repository.OrderRepository;
import pl.sda.shop.onlineshop.service.validation.OrderValidator;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderValidator orderValidator;
    private final OrderRepository orderRepository;
    private final UserService userService;

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order with id = %d not found in database", id)));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllByUser(String username) {
        return orderRepository.findOrdersByUserUsername(username).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order with username = %d not found in database", username)));
    }

    public Order save(Order order, String username) {
        if (orderValidator.isAvailable(order.getShoppingCart())){
            User user = userService.findByUsername(username);
            order.setUser(user);
            order.setOrderDate(java.time.LocalDateTime.now());
            order.setOrderStatus(OrderStatus.ORDERED);
            return orderRepository.save(order);
        }
        throw new ProductNotAvailable(String.format("One of the products is not available"));
    }

    public boolean deleteById(Long id) {
        orderRepository.deleteById(id);
        return true;
    }
}
