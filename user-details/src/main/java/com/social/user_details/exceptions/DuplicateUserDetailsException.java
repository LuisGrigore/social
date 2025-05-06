package com.social.user_details.exceptions;
import com.social.common.exceptions.DuplicateException;

public class DuplicateUserDetailsException extends DuplicateException {
    public DuplicateUserDetailsException(String message) {
        super(message);
    }
}
