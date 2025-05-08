package com.social.gateway.services.implementations;

import com.social.common.dtos.ValidateRequest;
import com.social.common.dtos.ValidateResponse;
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

    public Mono<ValidateResponse> validateToken(final String token){
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8082/auth/validate")
                .bodyValue(new ValidateRequest(token))
                .retrieve()
                .onStatus(HttpStatusCode::isError,
                        response -> Mono.error(new TokenInvalidException("Invalid token."))
                )
                .bodyToMono(ValidateResponse.class);
    }

}
