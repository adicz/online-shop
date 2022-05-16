package pl.sda.shop.onlineshop.exceptions.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
