package de.telran.onlineshopgarden.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsGlobalSwaggerConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Разрешаем cookies
        config.addAllowedOriginPattern("*"); // Лучше, чем addAllowedOrigin("*") в новых версиях
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Применить ко всем эндпоинтам (или только к Swagger)
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}