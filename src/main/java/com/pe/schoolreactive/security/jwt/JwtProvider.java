package com.pe.schoolreactive.security.jwt;

import com.pe.schoolreactive.service.dto.UserSession;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@Log4j2
@Component
public class JwtProvider {

    @Value("${application.security.authentication.jwt.jwtSecret}")
    private String jwtSecret;

    public final Function<Authentication, String> generateToken = auth -> Jwts.builder()
            .setSubject(((UserSession) auth.getPrincipal()).getUsername())
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.ES256, jwtSecret)
            .compact();

    public final Predicate<String> validateToken = s -> {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(s);
            return true;
        } catch (SignatureException e) {
            log.error("Firma del JWT inválida: {} ", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("JWT token inválido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token no soportado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT Cadena de reclamaciones vacía: {}", e.getMessage());
        }
        return false;
    };

    public final UnaryOperator<String> getUserNameFromJwtToken = name -> Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(name)
            .getBody().getSubject();

}
