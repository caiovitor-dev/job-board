package com.caio.job_board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skill_id")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "skills")
    private List<Resume> resumes;
}
