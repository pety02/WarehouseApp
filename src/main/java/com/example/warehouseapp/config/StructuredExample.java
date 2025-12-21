package com.example.warehouseapp.config;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Schema;
import com.google.genai.types.Type;

import java.util.Map;
import java.util.List;

// TODO: make a schema/s exporter/s class for Gemini and use it everywhere you should have predictions
public class StructuredExample {

    public void run(Client client, String prompt) {

        // Define Schema for response
        Schema.Builder builder = Schema.builder();
        builder.type(Type.Known.OBJECT);
        builder.properties(
                Map.of(
                        "date", Schema.builder()
                                .type(Type.Known.STRING)
                                .build(),
                        "predicted_stock", Schema.builder()
                                .type(Type.Known.INTEGER)
                                .build(),
                        "notes", Schema.builder()
                                .type(Type.Known.STRING)
                                .build()
                )
        ). required(List.of("date", "predicted_stock", "notes"));
        Schema schema = builder
                .build();

        // Add Schema to content config
        GenerateContentConfig config = GenerateContentConfig.builder()
                .responseMimeType("application/json")
                .responseSchema(schema)
                .build();

        GenerateContentResponse response = client.models.generateContent(
                "gemma-3-1b-it", prompt, config); // check if this is the correct name of the model

        String jsonResponse = response.text();
        System.out.println("JSON Output: " + jsonResponse);
    }
}