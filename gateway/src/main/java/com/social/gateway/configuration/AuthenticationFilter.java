package com.social.gateway.configuration;

import com.social.common.dtos.ValidateResponse;
import com.social.common.exceptions.TokenInvalidException;
import com.social.gateway.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtService jwtUtil;

    public AuthenticationFilter(RouteValidator validator, JwtService jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "Missing authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7);
            }

            return jwtUtil.validateToken(authHeader)
                    .flatMap(validateResponse -> {
                        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                .header("id", validateResponse.id().toString())
                                .header("username", validateResponse.username())
                                .headers(headers -> headers.remove(HttpHeaders.AUTHORIZATION))
                                .build();

                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
                    })
                    .onErrorResume(TokenInvalidException.class,
                            ex -> onError(exchange, "Invalid token", HttpStatus.UNAUTHORIZED));

        };
    }
    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        exchange.getResponse().getHeaders().add(HttpHeaders.CONTENT_TYPE, "text/plain; charset=UTF-8");
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    public static class Config {

    }

}