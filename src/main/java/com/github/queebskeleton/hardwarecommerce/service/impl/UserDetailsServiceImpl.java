package com.github.queebskeleton.hardwarecommerce.service.impl;

import java.util.Arrays;

import com.github.queebskeleton.hardwarecommerce.repository.UserJpaRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.github.queebskeleton.hardwarecommerce.entity.User user =
            userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with given username"));

        return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .authorities(
                Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getType().toString())))
            .build();

    }
    
}
