package flea.market.server.exception.exception;

import flea.market.server.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginFailureException extends RuntimeException {

    private final ErrorCode errorCode;

}