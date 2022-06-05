package pl.sda.shop.onlineshop.exception.user;

public class ContentTypeException extends RuntimeException {

    public ContentTypeException(String fileFormat) {
        super(String.format(
                "Format '%s' is wrong, we provide image",
                fileFormat
        ));
    }
}
