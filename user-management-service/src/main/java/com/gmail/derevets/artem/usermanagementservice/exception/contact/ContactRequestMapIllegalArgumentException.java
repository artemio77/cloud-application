package com.gmail.derevets.artem.usermanagementservice.exception.contact;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContactRequestMapIllegalArgumentException extends ContactException {
    public ContactRequestMapIllegalArgumentException() {
        super();
    }

    public ContactRequestMapIllegalArgumentException(String message) {
        super(message);
    }

    public ContactRequestMapIllegalArgumentException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ContactRequestMapIllegalArgumentException(Throwable throwable) {
        super(throwable);
    }
}
