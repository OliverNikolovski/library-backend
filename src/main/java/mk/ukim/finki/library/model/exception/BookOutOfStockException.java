package mk.ukim.finki.library.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookOutOfStockException extends RuntimeException {
    public BookOutOfStockException(String name) {
        super(String.format("The book %s is out of stock", name));
    }
}
