package com.caio.job_board.exception.global;

import com.caio.job_board.dto.ErroResponseDTO;
import com.caio.job_board.exception.ExistsEmailException;
import com.caio.job_board.exception.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {




    @ExceptionHandler(ExistsEmailException.class)
    public ResponseEntity<ErroResponseDTO> existsEmailHandler(ExistsEmailException e){

        ErroResponseDTO erroResponse = new ErroResponseDTO(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErroResponseDTO> roleNotFoundException(RoleNotFoundException e){

        ErroResponseDTO erroResponse = new ErroResponseDTO(HttpStatus.NOT_FOUND.value(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErroResponseDTO> unauthorizedException(BadCredentialsException e){

        ErroResponseDTO erroResponseDTO = new ErroResponseDTO(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erroResponseDTO);
    }
}
