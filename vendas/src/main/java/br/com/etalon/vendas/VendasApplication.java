package br.com.etalon.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EntityScan({"br.com.etalon.core.model"})
@EnableJpaRepositories({"br.com.etalon.core.repository.jpa"})
@EnableRedisRepositories({"br.com.etalon.core.repository.redis"})
public class VendasApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                .withZone(ZoneId.of("GMT-3"));
    }

}
