package com.FirstSpingApp.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedOrigins("http://e-commerce-dev-front.herokuapp.com").allowedMethods("*").allowedHeaders("*").allowCredentials(false).maxAge(3600);
    }

}
