package com.mockmate.room_ws_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply CORS to all endpoints
                        .allowedOrigins("https://www.mockmate.live", "https://backend.mockmate.live", "http://localhost:3000") // List of allowed origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specific methods
                        .allowedHeaders("*") // Allow all headers
                        .exposedHeaders("Authorization") // Expose specific headers if needed
                        .allowCredentials(false); // Credentials not allowed with '*' origins
            }
        };
    }
}
