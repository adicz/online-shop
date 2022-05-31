package pl.sda.shop.onlineshop.service.validation;

import pl.sda.shop.onlineshop.model.Product;
import pl.sda.shop.onlineshop.model.ProductCount;
import pl.sda.shop.onlineshop.model.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartValidator {

    public static boolean isValid(ShoppingCart shoppingCart){
        List<ProductCount> productCountList = shoppingCart.getProductCounts();
        BigDecimal productPrice = BigDecimal.valueOf(0);
        //for each
        for (ProductCount p:productCountList) {
            Product product = p.getProduct();
            productPrice = productPrice.add(product.getPrice().multiply(BigDecimal.valueOf(p.getCount())));
        }
            return shoppingCart.getTotalPrice().compareTo(productPrice) == 0;
    }
}
