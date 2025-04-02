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

                // Разрешить CORS только для Swagger и API-доков
                registry.addMapping("/swagger-ui/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET");

                registry.addMapping("/v3/api-docs/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET");

                // (по желанию) добавить основное API, если ты тестируешь из Swagger
                registry.addMapping("/users/**")
                        .allowedOrigins("*") // Или укажи конкретный хост: "https://onlineshopgarden-production.up.railway.app"
//                        .allowedOrigins("https://onlineshopgarden-production.up.railway.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // обязательно OPTIONS!
                        .allowedHeaders("*")
                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Headers")
                        .allowCredentials(false); // если не используешь cookie
            }
        };
    }
}
