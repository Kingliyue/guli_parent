package com.atliyue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

@Configuration
public class CrossOrigin {
    @Bean
    public CorsWebFilter corsWebFilter(){
        CorsConfiguration  configuration = new CorsConfiguration();
        configuration.addAllowedMethod("*");
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource  source = new UrlBasedCorsConfigurationSource ();
        source.registerCorsConfiguration("/**",configuration);
        return new CorsWebFilter((CorsConfigurationSource) source);
    }

}
