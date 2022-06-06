package pl.sda.shop.onlineshop.exception.product;

public class ProductNotAvailable extends RuntimeException{

    public ProductNotAvailable(String message){
        super(message);
    }
}
