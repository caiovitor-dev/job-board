package com.caio.job_board.service;
import com.caio.job_board.dto.AuthenticationDTO;
import com.caio.job_board.entity.User;
import com.caio.job_board.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public void authenticate(AuthenticationDTO dto,HttpSession httpSession){


        var emailPassword = new UsernamePasswordAuthenticationToken(dto.email(),dto.password());
        Authentication authenticate = authenticationManager.authenticate(emailPassword);


        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authenticate);

        SecurityContextHolder.setContext(context);

        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,context);


    }

}