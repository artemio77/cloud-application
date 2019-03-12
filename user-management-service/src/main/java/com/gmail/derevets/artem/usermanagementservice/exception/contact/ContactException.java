package com.gmail.derevets.artem.usermanagementservice.exception.contact;

import com.gmail.derevets.artem.usermanagementservice.exception.CloudApplicationException;

public class ContactException extends CloudApplicationException {
    public ContactException() {
        super();
    }

    public ContactException(String message) {
        super(message);
    }

    public ContactException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ContactException(Throwable throwable) {
        super(throwable);
    }

}
