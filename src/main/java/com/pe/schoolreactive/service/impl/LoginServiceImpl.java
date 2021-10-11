package com.pe.schoolreactive.service.impl;

import com.pe.schoolreactive.repository.UserRepository;
import com.pe.schoolreactive.service.dto.UserSession;
import com.pe.schoolreactive.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return UserSession.build(repository.findUserByActiveAndUsername(Constants.ACTIVE, s)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no ha sido encontrado: " + s))
        );
    }
}
