package pl.sda.shop.onlineshop.exception.category;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(Long id){
        super(String.format(
                "Category with id = %d not found in database",
                id
        ));
    }

    public CategoryNotFoundException(String name) {
        super(String.format(
                "Category with name = %s not found in database",
                name
        ));
    }
}
