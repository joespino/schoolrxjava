package com.pe.schoolreactive.service.impl;

import com.pe.schoolreactive.domain.User;
import com.pe.schoolreactive.repository.UserRepository;
import com.pe.schoolreactive.security.jwt.JwtProvider;
import com.pe.schoolreactive.service.AuthService;
import com.pe.schoolreactive.service.dto.JwtBody;
import com.pe.schoolreactive.service.dto.UserDto;
import com.pe.schoolreactive.service.dto.UserSession;
import com.pe.schoolreactive.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository repository;
    private JwtProvider jwtProvider;

    @Override
    public JwtBody signin(UserDto userDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken.apply(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> optionalUser = repository.findUserByActiveAndUsername(Constants.ACTIVE,
                userDetails.getUsername());
        User user;
        UserAccess userAccess = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            Long profileId = user.getProfile().getPerfId();
            List<PermisoDTO> permits = permitDTORepository.buscarPermisosPorPerfil(profileId);
            String nombreUsuario = user.getPerson().getPersNombre();
            if (user.getCompany() != null) {
                nombreUsuario = user.getCompany().getEmprRazonsocial();
            }
            userAccess = new UserSession(nombreUsuario, user.getUsuEmail(), user.getUsuAlta(), profileId, permits);
        }
    }
}
