package com.yang.nbaplayerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class PlayerNotFoundException extends HttpStatusCodeException {

    public PlayerNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public PlayerNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
