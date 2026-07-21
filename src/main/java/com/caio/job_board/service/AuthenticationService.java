package com.caio.job_board.service;
import com.caio.job_board.dto.AuthenticationDTO;
import com.caio.job_board.dto.TokenResponse;
import com.caio.job_board.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public TokenResponse authenticate(AuthenticationDTO dto){


        var emailPassword = new UsernamePasswordAuthenticationToken(dto.email(),dto.password());
        Authentication authenticate = authenticationManager.authenticate(emailPassword);

        UserDetails user = (UserDetails) authenticate.getPrincipal();

        assert user != null;

        String access = jwtService.generateAccessToken(user);
        return new TokenResponse(access);

    }


}