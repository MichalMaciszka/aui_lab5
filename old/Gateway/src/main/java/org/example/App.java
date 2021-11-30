package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("companies", r -> r
                        .host("172.28.0.3:7777")
                        .and()
                        .path("/api/companies/{company}", "/api/companies")
                        .uri("http://172.28.0.3:8081"))
                .route("employees", r -> r
                        .host("172.28.0.3:7777")
                        .and()
                        .path("/api/employees", "/api/employees/**", "/api/companies/{name}/employees", "/api/companies/{name}/employees/**")
                        .uri("http://172.28.0.3:8082"))
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
