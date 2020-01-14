package com.FirstSpingApp.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedOrigins("https://e-commerce-dev-front.herokuapp.com", "https://e-commerce-dev-front.000webhostapp.com").allowedMethods("*").allowedHeaders("*").allowCredentials(true).maxAge(3600);
    }

}
