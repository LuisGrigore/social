package com.social.gateway.exceptions;
import com.social.common.exceptions.DuplicateException;

public class DuplicateUserException extends DuplicateException {
    public DuplicateUserException(String message) {
        super(message);
    }
}
