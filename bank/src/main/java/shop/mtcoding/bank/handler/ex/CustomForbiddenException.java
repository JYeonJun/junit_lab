package shop.mtcoding.bank.handler.ex;

import lombok.Getter;

import java.util.Map;

@Getter
public class CustomForbiddenException extends RuntimeException{

    public CustomForbiddenException(String message) {
        super(message);
    }
}
