package pl.sda.shop.onlineshop.exception.product;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super(String.format(
                "Product with id = %d not found in database",
                id
        ));
    }
}
