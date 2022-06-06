package pl.sda.shop.onlineshop.exception.shippingMethod;

public class ShippingMethodNotFoundException extends RuntimeException {
    public ShippingMethodNotFoundException(String message) {
        super(message);
    }
}
