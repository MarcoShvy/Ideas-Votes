package com.example.Brainstoming.Ideas.service;

import com.example.Brainstoming.Ideas.model.idea.Idea;
import com.example.Brainstoming.Ideas.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAIService {
    private final RestTemplate restTemplate;

    @Autowired
    private IdeaRepository ideaRepository;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String sendToGPT(String prompt) {
        String apiKey = "SUA_API_KEY"; // Coloque aqui sua chave da API

        // URL da API do OpenAI
        String url = "https://api.openai.com/v1/completions";

        // Criar o corpo da requisição
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 500);

        // Definir os headers da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        // Criar a requisição HTTP
        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();

        // Enviar a requisição
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        // Processar a resposta
        JSONObject responseBody = new JSONObject(response.getBody());
        return responseBody.getJSONArray("choices").getJSONObject(0).getString("text");
    }

    public String promptFormat(List<Idea> ideas ) {
        StringBuilder prompt = new StringBuilder("Preciso que de algumas sugestões para melhorar estas ideias");
        for (Idea idea : ideas) {
            prompt.append(" - ").append(idea.getContent()).append("/n");
        }
        return prompt.toString();
    }

    public void saveImprovedIdeas() {
        List<Idea> ideas = ideaRepository.findAll();

        String prompt = promptFormat(ideas);

        String improvedIdeas = sendToGPT(prompt);

        for (Idea idea : ideas) {
            idea.setImprovedIdea(improvedIdeas);
            ideaRepository.save(idea);
        }

    }
}
