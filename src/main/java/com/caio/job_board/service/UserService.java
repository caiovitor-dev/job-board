package com.caio.job_board.service;
import com.caio.job_board.entity.Role;
import com.caio.job_board.entity.User;
import com.caio.job_board.enums.NameRole;
import com.caio.job_board.enums.StatusAccount;
import com.caio.job_board.exception.ExistsEmailException;
import com.caio.job_board.exception.RoleNotFoundException;
import com.caio.job_board.repository.RoleRepository;
import com.caio.job_board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user){

        if(userRepository.existsByEmail(user.getEmail())){
            throw new ExistsEmailException("Já existe esse Email cadastrado");
        }

        String password = user.getPassword();
        Role role = roleRepository.findByName(NameRole.ROLE_USER).get();


        user.setPassword(passwordEncoder.encode(password));
        user.setStatusAccount(StatusAccount.ATIVA);
        user.setRoles(Collections.singletonList(role));

        return userRepository.save(user);
    }


    @Transactional
    public User getUserByEmail(String email){
        User user= userRepository.findByEmail(email);

        user.getRoles().size();
        return user;
    }


}
