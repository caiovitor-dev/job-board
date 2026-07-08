package com.caio.job_board.entity;
import com.caio.job_board.enums.NameRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NameRole name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}