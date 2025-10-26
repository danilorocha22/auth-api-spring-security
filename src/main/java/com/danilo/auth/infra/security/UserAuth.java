package com.danilo.auth.infra.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.danilo.auth.domain.user.User;
import com.danilo.auth.domain.user.UserRole;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserAuth implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (this.user.getRole() == UserRole.ADMIN)
                ? List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"))
                : List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the password used for authentication (never {@code null}).
     * <p>
     * In case you are using a
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}
     * it
     * is not necessary for the User to have a password (null can be returned). In
     * case you are using a
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}
     * with a UserDetailsService it
     * is more convenient to return null.
     * <p>
     * This implementation returns null as it is not necessary for the User to have
     * a password.
     *
     * @return the password for the user (never {@code null})
     */
    @Override
    public String getPassword() {
        // It is not necessary for the User to have a password (null can be returned)
        // In case you are using a DaoAuthenticationProvider with a UserDetailsService
        // it is more convenient to return null
        return null;
    }
}
