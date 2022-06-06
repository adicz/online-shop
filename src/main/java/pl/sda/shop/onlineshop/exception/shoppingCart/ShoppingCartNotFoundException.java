package pl.sda.shop.onlineshop.exception.shoppingCart;

public class ShoppingCartNotFoundException extends RuntimeException{
    public ShoppingCartNotFoundException(String message){
        super(message);
    }
}
