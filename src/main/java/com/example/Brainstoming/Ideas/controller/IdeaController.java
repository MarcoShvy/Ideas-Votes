package com.example.Brainstoming.Ideas.controller;

import com.example.Brainstoming.Ideas.model.idea.Idea;
import com.example.Brainstoming.Ideas.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/ideas")
public class IdeaController {

    @Autowired
    private IdeaRepository ideaRepository;

    @PostMapping("/{id}/vote")
    public ResponseEntity<Idea> vote(@PathVariable Long id) {
        Optional<Idea> ideaOptional = ideaRepository.findById(id);
        return ResponseEntity.ok().build();
    }
}
