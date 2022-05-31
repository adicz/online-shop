package pl.sda.shop.onlineshop.exception.shoppingCart;

public class PriceNotValidException extends RuntimeException{
    public PriceNotValidException(String message){
        super(message);
    }
}
