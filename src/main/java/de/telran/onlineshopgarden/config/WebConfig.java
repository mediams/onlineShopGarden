package de.telran.onlineshopgarden.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                // Для Swagger UI
                registry.addMapping("/swagger-ui/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET")
                        .allowedHeaders("Content-Type", "api_key", "Authorization")
                        .allowCredentials(false);

                // Для OpenAPI JSON
                registry.addMapping("/v3/api-docs/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET")
                        .allowedHeaders("Content-Type", "api_key", "Authorization")
                        .allowCredentials(false);

                // Для основного API, если нужно тестировать "Try it out"
                registry.addMapping("/**") // либо "/api/**", если используешь префикс
                        .allowedOrigins("*") // или конкретный домен
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("Content-Type", "api_key", "Authorization")
                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Headers")
                        .allowCredentials(false);
            }
        };
    }
}