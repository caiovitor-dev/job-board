package com.caio.job_board.controller;

import com.caio.job_board.dto.RoleCreateDTO;
import com.caio.job_board.entity.Role;
import com.caio.job_board.mapper.RoleMapper;
import com.caio.job_board.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody RoleCreateDTO dto){
        Role role = roleService.saveRole(roleMapper.toEntity(dto));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(role.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
