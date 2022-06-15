package com.dsm.dcs.security;

import com.dsm.dcs.exception.handler.AuthenticationEntryPointImpl;
import com.dsm.dcs.security.jwt.JwtTokenProvider;
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

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final JwtTokenProvider jwtTokenProvider;

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
                .antMatchers(HttpMethod.GET, "/delivery").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/delivery/**").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.DELETE, "/delivery/**").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.PATCH, "/delivery/**/**").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/users/search").hasAnyRole("ROLE_TEACHER")
                .antMatchers(HttpMethod.GET, "/delivery/user").hasAnyRole("ROLE_USER")
                .antMatchers(HttpMethod.POST, "/delivery").hasAnyRole("ROLE_COURIER")
                .antMatchers(HttpMethod.GET, "/delivery/null/user").hasAnyRole("ROLE_USER", "ROLE_TEACHER")
                .anyRequest().permitAll()

                .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and().apply(new FilterConfig(jwtTokenProvider));
    }
}
