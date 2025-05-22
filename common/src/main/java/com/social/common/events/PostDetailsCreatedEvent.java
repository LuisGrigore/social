package com.social.common.events;

public record PostDetailsCreatedEvent(
    Long id,
    Long owner,
    String postName,
    String contentUrl
) {}
