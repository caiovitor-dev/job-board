package com.caio.job_board.controller;

import com.caio.job_board.dto.AuthenticationDTO;
import com.caio.job_board.dto.TokenResponse;
import com.caio.job_board.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid AuthenticationDTO dto){

        TokenResponse authenticate = authenticationService.authenticate(dto);
        return ResponseEntity.ok(authenticate);

    }

}
