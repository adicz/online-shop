package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exception.order.OrderNotFoundException;
import pl.sda.shop.onlineshop.model.Order;
import pl.sda.shop.onlineshop.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order with id = %d not found in database", id)));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public boolean deleteById(Long id) {
        orderRepository.deleteById(id);
        return true;
    }
}
