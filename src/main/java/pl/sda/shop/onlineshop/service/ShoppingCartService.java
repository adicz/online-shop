package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exception.category.CategoryNotFoundException;
import pl.sda.shop.onlineshop.exception.shoppingCart.ShoppingCartNotFoundException;
import pl.sda.shop.onlineshop.model.ShoppingCart;
import pl.sda.shop.onlineshop.repository.ShoppingCartRepository;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart findById(Long id) {
        return shoppingCartRepository.findById(id).orElseThrow(
                () -> new ShoppingCartNotFoundException(String.format("ShoppingCart with id = %d not found in database", id)));
    }

    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public boolean deleteById(Long id) {
        shoppingCartRepository.deleteById(id);
        return true;
    }

}
