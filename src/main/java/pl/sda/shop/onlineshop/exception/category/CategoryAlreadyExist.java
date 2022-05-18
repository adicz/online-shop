package pl.sda.shop.onlineshop.exception.category;

public class CategoryAlreadyExist extends RuntimeException{

    public CategoryAlreadyExist(String message){
        super(message);
    }
}
