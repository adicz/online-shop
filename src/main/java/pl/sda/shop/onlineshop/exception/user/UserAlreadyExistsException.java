package pl.sda.shop.onlineshop.exception.user;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username, String email) {
        super(String.format(
                "User with username '%s' or email '%s' already exist in database",
                username,
                email
        ));
    }
}
