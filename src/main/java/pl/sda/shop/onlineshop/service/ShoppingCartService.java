package pl.sda.shop.onlineshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.shop.onlineshop.exception.shoppingCart.PriceNotValidException;
import pl.sda.shop.onlineshop.exception.shoppingCart.ShoppingCartNotFoundException;
import pl.sda.shop.onlineshop.model.ShoppingCart;
import pl.sda.shop.onlineshop.repository.ShoppingCartRepository;
import pl.sda.shop.onlineshop.service.validation.ShoppingCartValidator;


@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCartValidator shoppingCartValidator;
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart findById(Long id) {
        return shoppingCartRepository.findById(id).orElseThrow(
                () -> new ShoppingCartNotFoundException(id));
    }

    public ShoppingCart save(ShoppingCart shoppingCart) {
        if (shoppingCartValidator.isValid(shoppingCart)){
            return shoppingCartRepository.save(shoppingCart);
        }
            throw new PriceNotValidException(String.format("Price not valid"));
    }

    public boolean deleteById(Long id) {
        shoppingCartRepository.deleteById(id);
        return true;
    }

}
