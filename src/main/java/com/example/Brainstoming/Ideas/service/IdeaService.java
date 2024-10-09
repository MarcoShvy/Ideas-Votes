package com.example.Brainstoming.Ideas.service;

import com.example.Brainstoming.Ideas.model.idea.Idea;
import com.example.Brainstoming.Ideas.repository.IdeaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdeaService {

    @Autowired
    private IdeaRepository ideaRepository;

    public void vote(Long id) {
        Optional<Idea> ideaOptional = ideaRepository.findById(id);
        Idea idea = ideaOptional.get();
        idea.setVotes(idea.getVotes() + 1);
        ideaRepository.save(idea);
    }
}
