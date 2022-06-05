package pl.sda.shop.onlineshop.exception.category;

public class CategoryAlreadyExistsException extends RuntimeException{

    public CategoryAlreadyExistsException(String name){
        super(String.format(
                "Category with name '%s' already exist in database",
                name
        ));
    }
}
