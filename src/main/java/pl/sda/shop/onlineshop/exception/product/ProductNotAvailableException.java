package pl.sda.shop.onlineshop.exception.product;

public class ProductNotAvailableException extends RuntimeException {

    public ProductNotAvailableException() {
        super("One of the products is not available");
    }
}
