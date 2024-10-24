package com.example.Brainstoming.Ideas.repository;

import com.example.Brainstoming.Ideas.model.idea.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
}
