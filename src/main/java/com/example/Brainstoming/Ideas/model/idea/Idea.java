package com.example.Brainstoming.Ideas.model.idea;

import com.example.Brainstoming.Ideas.model.session.Session;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private Integer votes = 0;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    public Integer getVotes() {
        return votes;
    }
}
