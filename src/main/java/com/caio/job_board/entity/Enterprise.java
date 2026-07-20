package com.caio.job_board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "enterprises")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "enterprise")
    private List<Job> jobs;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String description;

    @CreatedDate
    private LocalDateTime dateCreation;

    @LastModifiedDate
    private  LocalDateTime dateUpdate;
}
