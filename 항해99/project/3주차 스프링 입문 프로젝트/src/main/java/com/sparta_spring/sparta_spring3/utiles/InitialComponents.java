package com.sparta_spring.sparta_spring3.utiles;

import com.sparta_spring.sparta_spring3.components.Components;
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
