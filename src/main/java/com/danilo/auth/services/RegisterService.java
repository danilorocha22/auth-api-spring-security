package com.danilo.auth.services;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.danilo.auth.domain.user.RegisterInput;
import com.danilo.auth.domain.user.User;
import com.danilo.auth.error.NegocioException;
import com.danilo.auth.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public User register(RegisterInput registerInput) {
        validarUsuarioExistente(registerInput);
        String encryptedPassword = this.encoder.encode(registerInput.password());
        User newUser = User.builder()
                .login(registerInput.login())
                .password(encryptedPassword)
                .role(registerInput.role()).build();
        return repository.save(Objects.requireNonNull(newUser));
    }

    private void validarUsuarioExistente(RegisterInput registerInput) {
        if (repository.existsByLogin(registerInput.login()))
            throw new NegocioException(
                    "Usuário já possui cadastro como e-mail informado: %s.".formatted(registerInput.login()));
    }

}
