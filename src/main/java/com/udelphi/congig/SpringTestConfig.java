package com.udelphi.congig;

import com.udelphi.model.audit.SpringSecurityAuditorAware;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class SpringTestConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public SpringSecurityAuditorAware auditorProvider() {
        return new SpringSecurityAuditorAware();
    }
}
