package com.nick.reverseproxy.router;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nick.reverseproxy.handler.IndexHandler;

@Configuration
public class IndexRouter {

    @Bean
    public RouterFunction<ServerResponse> route(IndexHandler indexHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/")
                        .and(RequestPredicates.accept(MediaType.TEXT_HTML)), indexHandler::index);
    }
}
