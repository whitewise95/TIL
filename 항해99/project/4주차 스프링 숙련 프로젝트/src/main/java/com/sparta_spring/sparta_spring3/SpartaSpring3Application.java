package com.sparta_spring.sparta_spring3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ServletComponentScan("lecturer")
@EnableJpaAuditing
@SpringBootApplication
public class SpartaSpring3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpartaSpring3Application.class, args);
    }

}
