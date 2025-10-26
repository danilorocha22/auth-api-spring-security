package com.danilo.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danilo.auth.domain.user.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);

    boolean existsByLogin(String login);
}
