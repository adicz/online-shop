package pl.sda.shop.onlineshop.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.shop.onlineshop.exception.product.ProductNotAvailableException;
import pl.sda.shop.onlineshop.exception.product.ProductNotFoundException;
import pl.sda.shop.onlineshop.model.Order;
import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.model.ProductCount;
import pl.sda.shop.onlineshop.repository.ProductRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final ProductRepository productRepository;

    public boolean isAvailable(Order order) {
        List<ProductCount> productCountList = order.getShoppingCart().getProductCounts();
        for (ProductCount productCount : productCountList) {
            Product product = productCount.getProduct();
            Product productFromDB = productRepository.findById(product.getId()).orElseThrow(() -> new ProductNotFoundException(product.getId()));
            if (productFromDB.getAvailability() - productCount.getCount() < 0) {
                throw new ProductNotAvailableException();
            }
            productFromDB.setAvailability(productFromDB.getAvailability() - productCount.getCount());
            order.setPrice((order.getShoppingCart().getTotalPrice()).add(order.getShippingMethod().getPrice()));
        }
        return true;
    }
}
