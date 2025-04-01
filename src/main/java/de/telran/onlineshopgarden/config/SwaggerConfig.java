package de.telran.onlineshopgarden.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("https://onlineshopgarden-production.up.railway.app");
        server.setDescription("Production Railway Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Online Shop Garden API")
                        .version("1.0"))
                .servers(List.of(server));
    }
}
