package com.pe.schoolreactive.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtBody {
    private String token;
    private String type = "Bearer";
    private UserSession userAccess;
    private Collection<? extends GrantedAuthority> authorities;
}
