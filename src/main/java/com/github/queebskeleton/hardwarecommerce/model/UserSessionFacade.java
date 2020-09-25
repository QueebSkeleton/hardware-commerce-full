package com.github.queebskeleton.hardwarecommerce.model;

import com.github.queebskeleton.hardwarecommerce.entity.User;
import com.github.queebskeleton.hardwarecommerce.repository.UserJpaRepository;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSessionFacade {

    private final UserJpaRepository userJpaRepository;

    private User user;

    public User getUser() {
        if(user == null)
            user = userJpaRepository.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName())
                    .orElse(null);

        return user;
    }
    
}
