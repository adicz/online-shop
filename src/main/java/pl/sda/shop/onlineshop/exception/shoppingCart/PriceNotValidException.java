package pl.sda.shop.onlineshop.exception.shoppingCart;

public class PriceNotValidException extends RuntimeException {

    public PriceNotValidException() {
        super("Price not valid");
    }
}
