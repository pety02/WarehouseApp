package com.example.warehouseapp.config;

import com.google.genai.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfig {
    public static final String MODEL_NAME = "gemma-3-1b-it";
    public static final String MODEL_VERSION = "1.0.0";

    @Bean
    public Client geminiClient() {
        return Client.builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .build();
    }
}
