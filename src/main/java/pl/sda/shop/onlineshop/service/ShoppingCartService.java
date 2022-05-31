package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exception.shoppingCart.PriceNotValidException;
import pl.sda.shop.onlineshop.exception.shoppingCart.ShoppingCartNotFoundException;
import pl.sda.shop.onlineshop.model.ShoppingCart;
import pl.sda.shop.onlineshop.repository.ShoppingCartRepository;

import static pl.sda.shop.onlineshop.service.validation.ShoppingCartValidator.isValid;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart findById(Long id) {
        return shoppingCartRepository.findById(id).orElseThrow(
                () -> new ShoppingCartNotFoundException(String.format("ShoppingCart with id = %d not found in database", id)));
    }

    public ShoppingCart save(ShoppingCart shoppingCart) {
        if (isValid(shoppingCart)){
            return shoppingCartRepository.save(shoppingCart);
        }
            throw new PriceNotValidException(String.format("Price not valid"));
    }

    public boolean deleteById(Long id) {
        shoppingCartRepository.deleteById(id);
        return true;
    }

}
