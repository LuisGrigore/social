package com.social.follows.exceptions;

import com.social.common.exceptions.DuplicateException;

public class FollowDuplicateException extends DuplicateException {

    public FollowDuplicateException(String message) {
        super(message);
    }
}
