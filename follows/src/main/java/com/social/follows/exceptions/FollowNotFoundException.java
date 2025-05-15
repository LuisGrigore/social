package com.social.follows.exceptions;

import com.social.common.exceptions.NotFoundException;

public class FollowNotFoundException extends NotFoundException {
    public FollowNotFoundException(String message) {
        super(message);
    }
}
