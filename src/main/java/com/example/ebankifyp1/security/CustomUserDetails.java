package com.example.ebankifyp1.security;

import com.example.ebankifyp1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Assuming your User has a Role Enum or similar structure
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Customize based on your application
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Customize based on your application
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Customize based on your application
    }

    @Override
    public boolean isEnabled() {
        return true; // Customize based on your application
    }

    public User getUser() {
        return user; // Provide access to original User if needed
    }
}
