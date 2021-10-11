package com.pe.schoolreactive.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pe.schoolreactive.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserSession implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserSession build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new UserSession(user.getIduser().longValue(), user.getUsername(), user.getPassword(),
                authorities);
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
