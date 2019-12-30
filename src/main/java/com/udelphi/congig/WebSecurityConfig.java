package com.udelphi.congig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService customUserDetailsService;

    public WebSecurityConfig(UserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GlobalMethodSecurityConfiguration globalMethodSecurityConfiguration(){
        return new GlobalMethodSecurityConfiguration();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .anonymous().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/**").hasAnyRole("user", "admin")
                .antMatchers(HttpMethod.POST, "/**").hasAnyRole("user", "admin")
                .antMatchers(HttpMethod.PUT, "/**").hasAnyRole("user", "admin")
                .antMatchers(HttpMethod.PATCH, "/**").hasAnyRole("user", "admin")
                .antMatchers(HttpMethod.DELETE, "/**").hasAnyRole("user", "admin")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}