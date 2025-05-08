package com.social.gateway.services;

import com.social.common.dtos.ValidateResponse;
import com.social.common.exceptions.TokenInvalidException;
import reactor.core.publisher.Mono;

public interface JwtService {
    Mono<ValidateResponse> validateToken(final String token);
}
