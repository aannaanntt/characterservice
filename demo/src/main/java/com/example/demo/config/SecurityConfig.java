package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JWTAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**", "/swagger-ui/**", "/auth/**") // Ignore CSRF for H2 Console, Swagger UI, and /auth/** endpoints
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) // Ensure CSRF token is handled properly
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/**", "/h2-console/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll() // Permit access to /auth/**, H2 Console, and Swagger endpoints
                .anyRequest().authenticated()) // Require authentication for all other requests
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Use stateless sessions
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before the UsernamePasswordAuthenticationFilter
            .headers(headers -> headers
                .frameOptions().sameOrigin()); // Allow frames for H2 Console

        return httpSecurity.build();
    }
    
    
}
