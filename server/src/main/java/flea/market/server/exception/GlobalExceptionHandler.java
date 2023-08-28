package flea.market.server.exception;

import flea.market.server.exception.exception.LoginFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LoginFailureException.class)
    protected ResponseEntity<ErrorResponse> handleLoginFailureException() {
        return ErrorResponse.toResponseEntity(ErrorCode.UNAUTHORIZED_MEMBER);
    }

}
