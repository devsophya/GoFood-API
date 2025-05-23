package br.com.gofood.gofood.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenVerificationException extends RuntimeException{
    public TokenVerificationException() {
        super ("Error Validation Token.");
    }
}
//S