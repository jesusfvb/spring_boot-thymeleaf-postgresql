package com.backend.backend.controls.config;

import java.util.LinkedList;
import java.util.List;
import com.backend.backend.repositorys.Users;
import com.backend.backend.services.UsersServises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsI implements UserDetailsService {

    @Autowired
    UsersServises service;

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        if (arg0.equals("jesusfvbAdmin")) {
            List<GrantedAuthority> role = new LinkedList<>();
            role.add(new SimpleGrantedAuthority("Administrador"));
            return new User(arg0, new BCryptPasswordEncoder().encode("75jess58"), role);
        } else {
            Users privote = service.findUserByUseName(arg0);
            List<GrantedAuthority> role = new LinkedList<>();
            role.add(new SimpleGrantedAuthority(privote.getRol().name()));
            return new User(privote.getUserName(), privote.getPassword(), role);
        }
    }

}
