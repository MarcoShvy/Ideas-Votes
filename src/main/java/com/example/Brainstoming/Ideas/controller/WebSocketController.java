package com.example.Brainstoming.Ideas.controller;

import com.example.Brainstoming.Ideas.model.idea.Idea;
import com.example.Brainstoming.Ideas.repository.IdeaRepository;
import com.example.Brainstoming.Ideas.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private IdeaRepository ideaRepository;


    // Recebe a mensagem no destino /app/idea/{sessionId}
    @MessageMapping("/idea")
    // Envia a mensagem de volta para todos os clientes inscritos no tópico /topic/ideas/{sessionId}
    @SendTo("/topic/ideas")
    public Idea sendIdea(Idea idea){
        Idea savedIdea = ideaRepository.save(idea);
        return savedIdea;
    }
}
