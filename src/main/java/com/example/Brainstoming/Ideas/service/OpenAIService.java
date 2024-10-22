package com.example.Brainstoming.Ideas.service;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenAIService {
    private final RestTemplate restTemplate;
    private final String API_URL = "https://api.openai.com/v1/completions";
    private final String API_KEY = "sk-9xXLAS7-CSAPiFIkU7tUslGYAuUBrrMIbW-rNLfO9pT3BlbkFJz4Z6fwU0opZwaFneVxDPVPLA4tcFz_6RfzK4-9K9YA";

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getChatGPTResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY);

        // Corpo da requisição para a OpenAI
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 150);
        requestBody.put("temperature", 0.7);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // Faz a requisição para a API da OpenAI
        ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, entity, Map.class);

        // Processa a resposta da API
        if (response.getStatusCode() == HttpStatus.OK) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            if (!choices.isEmpty()) {
                return choices.get(0).get("text").toString();
            }
        }
        return "Erro ao gerar resposta.";
    }
}
