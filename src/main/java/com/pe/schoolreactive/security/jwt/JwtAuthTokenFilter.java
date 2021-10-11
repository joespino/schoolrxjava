package com.pe.schoolreactive.security.jwt;

import com.pe.schoolreactive.service.impl.LoginServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

@Log4j2
@RequiredArgsConstructor
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private JwtProvider jwtProvider;
    private LoginServiceImpl loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJWT.apply(request);
            if (jwt != null && jwtProvider.validateToken.test(jwt)) {
                String username = jwtProvider.getUserNameFromJwtToken.apply(jwt);
                UserDetails userDetails = loginService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            log.error("NO se puede configurar la autenticación de usuario: {}", ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private final Function<HttpServletRequest, String> getJWT = request -> {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    };
}
