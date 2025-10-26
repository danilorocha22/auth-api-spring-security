package com.danilo.auth.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.danilo.auth.domain.user.LoginInput;
import com.danilo.auth.domain.user.LoginOutput;
import com.danilo.auth.domain.user.User;
import com.danilo.auth.infra.security.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginOutput login(LoginInput loginInput) {
        var auth = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginInput.login(), loginInput.password()));

        var token = this.tokenService.generateToken((User) auth.getPrincipal());

        return new LoginOutput(token);
    }
}