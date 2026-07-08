package com.caio.job_board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.UUID;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "resumes")
public class Resume {


    @Column(name = "resume_id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String experiences;

    @Column(nullable = false,unique = true)
    private String cpf;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false,unique = true)
    private String telephone;

    @Column(nullable = false)
    private String academicBackground;

    @ManyToMany
    @JoinTable(
            name = "resume_skill",
            joinColumns = @JoinColumn(name = "resume_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

}
