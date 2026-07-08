package com.caio.job_board.service;

import com.caio.job_board.entity.Role;
import com.caio.job_board.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
}
