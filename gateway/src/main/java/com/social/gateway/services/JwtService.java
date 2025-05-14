package com.social.gateway.services;

import com.social.common.dtos.UserValidationResponse;
import reactor.core.publisher.Mono;

public interface JwtService {
    Mono<UserValidationResponse> validateToken(final String token);
}
