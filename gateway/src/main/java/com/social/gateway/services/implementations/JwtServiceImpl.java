package com.social.gateway.services.implementations;

import com.social.common.dtos.UserValidationRequest;
import com.social.common.dtos.UserValidationResponse;
import com.social.common.exceptions.TokenInvalidException;
import com.social.gateway.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {


    private final WebClient.Builder webClientBuilder;

    public Mono<UserValidationResponse> validateToken(final String token){
        return webClientBuilder.build()
                .post()
                .uri("http://AUTH/auth/validate")
                .bodyValue(new UserValidationRequest(token))
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> Mono.error(new TokenInvalidException("Invalid token."))
                )
                .bodyToMono(UserValidationResponse.class);
    }

}
