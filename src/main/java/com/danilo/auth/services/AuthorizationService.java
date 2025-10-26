package com.danilo.auth.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.danilo.auth.domain.user.User;
import com.danilo.auth.infra.security.UserAuth;
import com.danilo.auth.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserAuth loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username);
        return new UserAuth(user);
    }
}
