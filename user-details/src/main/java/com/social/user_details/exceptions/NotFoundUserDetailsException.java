package com.social.user_details.exceptions;

import com.social.common.exceptions.NotFoundException;

public class NotFoundUserDetailsException extends NotFoundException {
    public NotFoundUserDetailsException(String message) {
        super(message);
    }
}
