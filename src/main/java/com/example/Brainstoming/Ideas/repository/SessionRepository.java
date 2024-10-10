package com.example.Brainstoming.Ideas.repository;

import com.example.Brainstoming.Ideas.model.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findAllById(Session session);
}
