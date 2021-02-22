package com.ionisstm.intervenants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.ionisstm.intervenants.repository")
public class IntervenantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntervenantsApplication.class, args);
    }

}
