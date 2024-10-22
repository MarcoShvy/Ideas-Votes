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
    @SendTo("/topic/ideas")
    public String sendIdea(Idea idea){
        Idea savedIdea = ideaRepository.save(idea);
        return savedIdea.getContent() + "Votes: " + savedIdea.getVotes();
    }
}
