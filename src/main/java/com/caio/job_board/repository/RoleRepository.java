package com.caio.job_board.repository;

import com.caio.job_board.entity.Role;
import com.caio.job_board.enums.NameRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

   Optional<Role> findByName (NameRole name);
}
