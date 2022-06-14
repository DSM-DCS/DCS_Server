package com.dsm.dcs.security.jwt;

import com.dsm.dcs.entity.Role;
import com.dsm.dcs.entity.auth.RefreshToken;
import com.dsm.dcs.entity.auth.RefreshTokenRepository;
import com.dsm.dcs.exception.ExpiredJwtException;
import com.dsm.dcs.exception.InvalidJwtException;
import com.dsm.dcs.exception.NotRefreshTokenException;
import com.dsm.dcs.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-exp}")
    private Long accessExp;

    @Value("${jwt.refresh-exp}")
    private Long refreshExp;

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer";

    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(String id, Role role) {
        return generateToken(id, "access", accessExp, role.name());
    }

    public String generateRefreshToken(String id, Role role) {
        return refreshTokenRepository.save(RefreshToken.builder()
                .accountId(id)
                .refreshToken(generateToken(id, "refresh", refreshExp, role.name()))
                .role(role)
                .build()).getRefreshToken();
    }

    private String generateToken(String id, String type, Long exp, String role) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(id)
                .setHeaderParam("typ", type)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(HEADER);
        return parseToken(bearer);
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService
                .loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(PREFIX))
            return bearerToken.replace((PREFIX), "");
        return null;
    }
    public boolean validateToken(String token) {

        try {
            return getTokenBody(token).getExpiration().after(new Date());
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }

    }

    public boolean isRefreshToken(String token) {
        try {
            return getHeader(token).get("typ").equals("refresh");
        } catch (Exception e) {
            throw NotRefreshTokenException.EXCEPTION;
        }
    }

    public String getRole(String token) {
        return getTokenBody(token).get("role").toString();
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredJwtException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }

    private String getTokenSubject(String token) {
        try {
            return getTokenBody(token).getSubject();
        } catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }

    private JwsHeader getHeader(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getHeader();
    }

}
