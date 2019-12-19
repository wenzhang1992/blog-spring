package com.spring.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsFilterConfig {

    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguraion = new CorsConfiguration();

        corsConfiguraion.addAllowedOrigin("*");
        corsConfiguraion.addAllowedHeader("*");
        corsConfiguraion.addAllowedMethod("*");

        return corsConfiguraion;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);
    }
}
