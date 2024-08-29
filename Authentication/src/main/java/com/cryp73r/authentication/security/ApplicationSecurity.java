package com.cryp73r.authentication.security;

import com.cryp73r.authentication.security.jwt.AuthEntryPointJwt;
import com.cryp73r.authentication.security.jwt.AuthTokenFilter;
import com.cryp73r.authentication.security.jwt.CustomAccessDeniedHandler;
import com.cryp73r.authentication.security.service.ClientUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ApplicationSecurity {

    // Remove `ROLE_` prefix from the role names
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public AuthTokenFilter authenticateJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthEntryPointJwt unauthorizedHandler() {
        return new AuthEntryPointJwt();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new ClientUserDetailsService();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling(exception -> {exception.authenticationEntryPoint(unauthorizedHandler());
            exception.accessDeniedHandler(accessDeniedHandler());})
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/admin/**").hasAnyRole("ADMINISTRATOR", "SUPER_USER")
                .requestMatchers("/api/**").hasAnyRole("ADMINISTRATOR", "SUPER_USER", "GENERAL_USER")
                .anyRequest().permitAll()).authenticationProvider(authenticationProvider());
        http.sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ))
                .addFilterBefore(authenticateJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
