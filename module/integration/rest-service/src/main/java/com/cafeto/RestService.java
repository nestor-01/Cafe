package com.cafeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan("com.cafeto.core.application.entity")
@EnableJpaRepositories("com.cafeto.core.application.repository")
public class RestService {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-5"));
    }

    public static void main(String args[]) {
        SpringApplication.run(RestService.class, args);
    }
}
