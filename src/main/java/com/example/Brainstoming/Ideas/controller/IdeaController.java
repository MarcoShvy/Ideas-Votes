package com.example.Brainstoming.Ideas.controller;

import com.example.Brainstoming.Ideas.model.idea.Idea;
import com.example.Brainstoming.Ideas.repository.IdeaRepository;
import com.example.Brainstoming.Ideas.service.IdeaService;
import com.example.Brainstoming.Ideas.service.OpenAIService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/ideas")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/{id}/vote")
    public ResponseEntity<Idea> vote(@PathVariable Long id) {
        ideaService.vote(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Idea>> getAllIdeas() {
        List<Idea> allIdeas = ideaService.getIdeaRepository().findAll();
        return ResponseEntity.ok().body(allIdeas);
    }

    @GetMapping("/{id}")
    public ResponseEntity getIdea(@PathVariable Long id) {
        ideaService.getIdeaRepository().findById(id);
        return null;
    }

    @PostMapping("/generate-ia")
    public ResponseEntity<String> generateResponse(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");
        String response = openAIService.getChatGPTResponse(prompt);
        return ResponseEntity.ok(response);
    }
}
