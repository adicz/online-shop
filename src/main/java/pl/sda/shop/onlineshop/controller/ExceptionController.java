package pl.sda.shop.onlineshop.controller;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sda.shop.onlineshop.exception.ErrorMessage;

import java.time.LocalDateTime;

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

}
