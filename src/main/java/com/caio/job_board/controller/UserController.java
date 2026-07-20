package com.caio.job_board.controller;

import com.caio.job_board.dto.UserCreateDTO;
import com.caio.job_board.entity.User;
import com.caio.job_board.mapper.UserMapper;
import com.caio.job_board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
@PreAuthorize("hasRole('USER')")
public class UserController {

  private final UserMapper userMapper;
  private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserCreateDTO dto) {
        User user = userMapper.toEntity(dto);
        userService.saveUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


}
