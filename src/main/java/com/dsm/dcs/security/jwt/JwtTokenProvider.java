package com.dsm.dcs.security.jwt;

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

    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer";

    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(String id) {
        return generateToken(id, "access", jwtProperties.getAccessExp());
    }

    public String generateRefreshToken(String id) {
        String refreshToken = generateToken(id, "refresh", jwtProperties.getRefreshExp());
        refreshTokenRepository.save(RefreshToken.builder()
                .accountId(id)
                .refreshToken(refreshToken)
                .build());

        return refreshToken;
    }

    private String generateToken(String id, String type, Long exp) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setSubject(id)
                .claim("type", type)
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

    public ZonedDateTime getExpiredTime() {
        return ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp());
    }

    private Claims getTokenBody(String token) {

        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token).getBody();
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
        return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token).getHeader();
    }

}
