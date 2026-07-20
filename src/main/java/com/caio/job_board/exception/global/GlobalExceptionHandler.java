package com.caio.job_board.exception.global;
import com.caio.job_board.dto.ErroResponseDTO;
import com.caio.job_board.dto.ValidationErroResponseDTO;
import com.caio.job_board.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErroResponseDTO> methodArgumentNotValidHandler(MethodArgumentNotValidException e){

        HashMap<String,String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error ->
                        errors.put(error.getField(),error.getDefaultMessage())
                );


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ValidationErroResponseDTO(
                        HttpStatus.BAD_REQUEST.value(),
                        "Erro de validação",
                        LocalDateTime.now(),
                        errors
                )
        );

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponseDTO> exceptionHandler(Exception e){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErroResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(),
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(ExistsEmailException.class)
    public ResponseEntity<ErroResponseDTO> existsEmailHandler(ExistsEmailException e){

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErroResponseDTO(
                        HttpStatus.CONFLICT.value(),
                        e.getMessage(),
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErroResponseDTO> roleNotFoundHandler(RoleNotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroResponseDTO(
                        HttpStatus.NOT_FOUND.value()
                        ,e.getMessage(),
                        LocalDateTime.now())
                );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErroResponseDTO> unauthorizedHandler(BadCredentialsException e){


        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErroResponseDTO(
                        HttpStatus.UNAUTHORIZED.value(),
                        e.getMessage(),
                        LocalDateTime.now())
                );
    }

    @ExceptionHandler(ExistsCnpjException.class)
    public ResponseEntity<ErroResponseDTO> existsCnpjHandler(ExistsCnpjException e){

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErroResponseDTO(
                        HttpStatus.CONFLICT.value(),
                        e.getMessage(),
                        LocalDateTime.now())
                );
    }


}
