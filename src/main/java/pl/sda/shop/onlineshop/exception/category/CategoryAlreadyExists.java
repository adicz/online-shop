package pl.sda.shop.onlineshop.exception.category;

public class CategoryAlreadyExists extends RuntimeException{

    public CategoryAlreadyExists(String message){
        super(message);
    }
}
