package com.mockmate.room_ws_service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class EnvConfig {

    private static final Logger logger = LoggerFactory.getLogger(EnvConfig.class);

    @Autowired
    private Environment env;

    public String getAuthServiceUrl() {
        return env.getProperty("AUTH_SERVICE_URL", "http://default.example.com");
    }

    @PostConstruct
    public void logAuthServiceUrl() {
        logger.info("AUTH_SERVICE_URL: {}", getAuthServiceUrl());
    }
}
