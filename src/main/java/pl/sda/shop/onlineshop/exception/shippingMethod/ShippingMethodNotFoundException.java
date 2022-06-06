package pl.sda.shop.onlineshop.exception.shippingMethod;

public class ShippingMethodNotFoundException extends RuntimeException {

    public ShippingMethodNotFoundException(Long id) {
        super(String.format(
                "Shipping method with id = %d not fund",
                id
        ));
    }
}
