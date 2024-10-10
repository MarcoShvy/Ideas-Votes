package com.example.Brainstoming.Ideas.service;

import com.example.Brainstoming.Ideas.model.idea.Idea;
import com.example.Brainstoming.Ideas.model.session.Session;
import com.example.Brainstoming.Ideas.repository.IdeaRepository;
import com.example.Brainstoming.Ideas.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebSocketService {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private SessionRepository sessionRepository;


}
