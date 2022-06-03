package com.sparta_spring.sparta_spring3.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

@Configuration
public class InitialComponents {

    @Bean
    @ConfigurationProperties(prefix = "components")
    public Components components() {
        return new Components();
    }

}
