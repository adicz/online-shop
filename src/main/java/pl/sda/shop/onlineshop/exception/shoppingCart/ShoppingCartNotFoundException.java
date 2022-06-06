package pl.sda.shop.onlineshop.exception.shoppingCart;

public class ShoppingCartNotFoundException extends RuntimeException{

    public ShoppingCartNotFoundException(Long id){
        super(String.format(
                "ShoppingCart with id = %d not found in database",
                id
        ));
    }
}
