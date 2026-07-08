package com.caio.job_board.security;

import com.caio.job_board.entity.Role;
import com.caio.job_board.entity.User;
import com.caio.job_board.repository.UserRepository;
import com.caio.job_board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername (String username){
        User user = userService.getUserByEmail(username);

        if(user == null)
            throw new UsernameNotFoundException("Usuário não encontrado");

        return new org.springframework.security.core.userdetails
                .User(user.getEmail(),user.getPassword(),mapRolesToAuthority(user.getRoles()));
    }

    public Collection<GrantedAuthority> mapRolesToAuthority(List<Role> roles){
        return roles.stream()
                .map(role-> new SimpleGrantedAuthority(role.getName().toString())).collect(Collectors.toList());
    }
}
