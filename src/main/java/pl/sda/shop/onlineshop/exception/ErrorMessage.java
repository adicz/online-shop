package pl.sda.shop.onlineshop.exception;

import java.time.LocalDateTime;

public class ErrorMessage {

    public String status = "error";
    public String message;
    public LocalDateTime date;

    public ErrorMessage(String message, LocalDateTime date) {
        this.message = message;
        this.date = date;
    }

}
