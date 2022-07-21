package pl.sda.shop.onlineshop.exception.order;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super(String.format(
                "Order with id = %d not found in database",
                id));
    }

    public OrderNotFoundException(String username) {
        super(String.format(
                "Order with username '%s' not found in database",
                username));
    }
}
