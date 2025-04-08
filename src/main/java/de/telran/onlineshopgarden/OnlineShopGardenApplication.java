package de.telran.onlineshopgarden;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableScheduling
public class OnlineShopGardenApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        Map<String, Object> envMap = dotenv.entries().stream()
                .collect(Collectors.toMap(DotenvEntry::getKey, DotenvEntry::getValue));

        SpringApplication app = new SpringApplication(OnlineShopGardenApplication.class);
        app.setDefaultProperties(envMap);
        app.run(args);
    }

}
