package pl.sda.shop.onlineshop.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.shop.onlineshop.exception.product.ProductNotFoundExceptions;
import pl.sda.shop.onlineshop.exception.shoppingCart.PriceNotValidException;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.model.ProductCount;
import pl.sda.shop.onlineshop.model.ShoppingCart;
import pl.sda.shop.onlineshop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShoppingCartValidator {
    private final ProductRepository productRepository;

    public boolean isValid(ShoppingCart shoppingCart) {
        List<ProductCount> productCountList = shoppingCart.getProductCounts();
        BigDecimal productsPrice = BigDecimal.valueOf(0);
        //for each
        for (ProductCount productCount : productCountList) {
            Product product = productCount.getProduct();
            Product productFromDB = productRepository.findById(product.getId()).orElseThrow(() -> new ProductNotFoundExceptions("Product not found exception"));
            productsPrice = productsPrice.add(productFromDB.getPrice().multiply(BigDecimal.valueOf(productCount.getCount())));
        }
        return shoppingCart.getTotalPrice().compareTo(productsPrice) == 0;
    }
}
