package com.demo.oms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaAuditing
@EnableScheduling
public class OmsApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "oms");
        SpringApplication.run(OmsApplication.class, args);
    }

}
