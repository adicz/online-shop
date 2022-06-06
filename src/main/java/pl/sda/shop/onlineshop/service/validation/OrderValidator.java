package pl.sda.shop.onlineshop.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.shop.onlineshop.exception.product.ProductNotAvailableException;
import pl.sda.shop.onlineshop.exception.product.ProductNotFoundException;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.model.ProductCount;
import pl.sda.shop.onlineshop.model.ShoppingCart;
import pl.sda.shop.onlineshop.repository.ProductRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final ProductRepository productRepository;

    public boolean isAvailable(ShoppingCart shoppingCart) {
        List<ProductCount> productCountList = shoppingCart.getProductCounts();
        for (ProductCount productCount : productCountList) {
            Product product = productCount.getProduct();
            Product productFromDB = productRepository.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getId()));
            if (productFromDB.getAvailability() - productCount.getCount() < 0) {
                throw new ProductNotAvailableException();
            }
            productFromDB.setAvailability(productFromDB.getAvailability() - productCount.getCount());
        }
        return true;
    }
}
