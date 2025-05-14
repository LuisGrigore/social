package com.social.common.events;

public record PostCreateEvent(String contentUrl, Long owner, String postName) {
}
