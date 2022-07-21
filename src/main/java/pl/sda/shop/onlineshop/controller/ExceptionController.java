package pl.sda.shop.onlineshop.controller;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sda.shop.onlineshop.exception.ErrorMessage;
import pl.sda.shop.onlineshop.exception.ValidationErrorMessage;
import pl.sda.shop.onlineshop.exception.category.CategoryAlreadyExistsException;
import pl.sda.shop.onlineshop.exception.category.CategoryNotFoundException;
import pl.sda.shop.onlineshop.exception.order.OrderNotFoundException;
import pl.sda.shop.onlineshop.exception.product.ProductNotFoundException;
import pl.sda.shop.onlineshop.exception.role.RoleNotFoundException;
import pl.sda.shop.onlineshop.exception.shippingMethod.ShippingMethodNotFoundException;
import pl.sda.shop.onlineshop.exception.shoppingCart.PriceNotValidException;
import pl.sda.shop.onlineshop.exception.shoppingCart.ShoppingCartNotFoundException;
import pl.sda.shop.onlineshop.exception.user.ContentTypeException;
import pl.sda.shop.onlineshop.exception.user.UserAlreadyExistsException;
import pl.sda.shop.onlineshop.exception.user.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity<ErrorMessage> sizeLimitExceededException(SizeLimitExceededException e) {
        return ResponseEntity
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(new ErrorMessage(
                        HttpStatus.PAYLOAD_TOO_LARGE.toString(),
                        e.getMessage(),
                        LocalDateTime.now()));
    }

    @ExceptionHandler(ContentTypeException.class)
    public ResponseEntity<ErrorMessage> wrongFileTypeException(ContentTypeException e) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorMessage(
                        HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                        e.getMessage(),
                        LocalDateTime.now()));
    }

    @ExceptionHandler({
            CategoryNotFoundException.class,
            OrderNotFoundException.class,
            ProductNotFoundException.class,
            RoleNotFoundException.class,
            ShippingMethodNotFoundException.class,
            ShoppingCartNotFoundException.class,
            UserNotFoundException.class,
    })
    public ResponseEntity<ErrorMessage> notFoundException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(
                        HttpStatus.NOT_FOUND.toString(),
                        e.getMessage(),
                        LocalDateTime.now()));
    }

    @ExceptionHandler({
            CategoryAlreadyExistsException.class,
            UserAlreadyExistsException.class
    })
    public ResponseEntity<ErrorMessage> alreadyExistException(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorMessage(
                        HttpStatus.CONFLICT.toString(),
                        e.getMessage(),
                        LocalDateTime.now()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationErrorMessage> validationErrorException(MethodArgumentNotValidException e) {
        List<String> details = getAllErrorsDetails(e);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ValidationErrorMessage(
                        HttpStatus.CONFLICT.toString(),
                        "Validation error, see details",
                        LocalDateTime.now(),
                        details
                ));
    }

    private List<String> getAllErrorsDetails(MethodArgumentNotValidException e) {
        List<String> details = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            details.add(fieldName + ": " + errorMessage);
        });
        return details;
    }

    @ExceptionHandler({PriceNotValidException.class})
    public ResponseEntity<ErrorMessage> priceValidationException(PriceNotValidException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorMessage(
                        HttpStatus.CONFLICT.toString(),
                        e.getMessage(),
                        LocalDateTime.now()
                ));
    }

}
