package com.social.comments.exceptions;

import com.social.common.exceptions.NotFoundException;

public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException(String s) {
        super(s);
    }
}
