package com.caio.job_board.controller;

import com.caio.job_board.dto.AuthenticationDTO;
import com.caio.job_board.service.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody AuthenticationDTO dto,HttpSession httpSession){

        authenticationService.authenticate(dto,httpSession);
        return ResponseEntity.ok().build();

    }

}
