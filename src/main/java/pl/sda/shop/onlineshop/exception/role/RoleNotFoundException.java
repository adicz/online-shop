package pl.sda.shop.onlineshop.exception.role;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String name) {
        super(String.format(
                "Role with name '%s' not found in database",
                name
        ));
    }
}
