package com.example.perfume.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .mvcMatchers("/api//perfume/**", "/api//post/**", "/api//member/**", "/api//oauth/**",
                        "/api//survey/**", "/api//feedback/**",
                        "/api//api-docs/**", "/api/**", "/api//swagger-ui/**", "/api//board/**", "/api//report/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests(
                        (authorization) -> authorization.antMatchers("/api//perfume/**", "/api//post/**",
                                        "/api//member/**",
                                        "/api//oauth/**", "/api//survey/**", "/api//feedback/**").permitAll()
                                .antMatchers("/api//swagger-ui/**", "/api//api-docs/**", "/api//v3/api-docs/**",
                                        "/api//board/**",
                                        "/api//report/**").permitAll().anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
