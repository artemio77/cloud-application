package com.gmail.derevets.artem.usermanagementservice.exception.user;

import com.gmail.derevets.artem.usermanagementservice.exception.CloudApplicationException;

public class UserException extends CloudApplicationException {
    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UserException(Throwable throwable) {
        super(throwable);
    }
}
