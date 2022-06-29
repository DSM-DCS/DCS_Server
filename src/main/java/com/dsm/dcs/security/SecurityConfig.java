package com.dsm.dcs.security;

import com.dsm.dcs.error.CustomAuthenticationEntryPoint;
import com.dsm.dcs.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .cors()

                .and()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.PATCH, "/users/password").hasAnyRole("ROLE_USER")
                .antMatchers(HttpMethod.GET, "/users/verification-password").hasAnyRole("ROLE_USER")
                .antMatchers(HttpMethod.GET, "/users").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/users/search").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/users/mypage").hasAnyRole("ROLE_USER")
                .antMatchers(HttpMethod.GET, "/delivery").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/delivery/**").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.DELETE, "/delivery/**").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.PATCH, "/delivery/**/**").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/users/search").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/delivery/user").hasAnyRole("ROLE_USER")
                .antMatchers(HttpMethod.POST, "/delivery").hasAnyRole("ROLE_COURIER")
                .antMatchers(HttpMethod.GET, "/delivery/null/user").hasAnyRole("ROLE_USER", "ROLE_TEACHER")
                .antMatchers(HttpMethod.POST, "/post").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.PATCH, "/post/**").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.DELETE, "/post/**").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/post").hasAnyRole("ROLE_USER", "ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/post").hasAnyRole("ROLE_USER", "ROLE_TEACHER")
                .anyRequest().permitAll()

                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))

                .and()
                .apply(new FilterConfig(jwtTokenProvider, objectMapper));
    }
}
