package com.danilo.auth.domain.user;

public record RegisterInput(String login, String password, UserRole role) {
}
