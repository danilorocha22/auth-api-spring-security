package com.danilo.auth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danilo.auth.domain.user.LoginInput;
import com.danilo.auth.domain.user.LoginOutput;
import com.danilo.auth.domain.user.RegisterInput;
import com.danilo.auth.services.LoginService;
import com.danilo.auth.services.RegisterService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthenticationController {
    private final RegisterService registerService;
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginOutput> login(@RequestBody @Valid LoginInput loginInput) {
        var loginOutput = this.loginService.login(loginInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginOutput);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterInput registerRequest) {
        this.registerService.register(registerRequest);
        return ResponseEntity.ok().body("Usuário cadastrado com sucesso!");
    }
}
