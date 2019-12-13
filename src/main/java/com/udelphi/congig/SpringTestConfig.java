package com.udelphi.congig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringTestConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
