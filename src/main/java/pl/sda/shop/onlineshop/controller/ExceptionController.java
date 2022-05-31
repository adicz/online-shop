package pl.sda.shop.onlineshop.controller;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sda.shop.onlineshop.exception.ErrorMessage;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(SizeLimitExceededException.class)
    @ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
    public ErrorMessage sizeLimitExceededException(SizeLimitExceededException e) {
        return new ErrorMessage(
                e.getMessage(),
                LocalDateTime.now()
        );
    }

}
