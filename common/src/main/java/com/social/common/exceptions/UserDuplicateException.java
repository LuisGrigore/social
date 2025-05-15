package com.social.common.exceptions;

public class UserDuplicateException extends DuplicateException {
    public UserDuplicateException(String message) {
        super(message);
    }
}
