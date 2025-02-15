package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	private final JWTService jwtService;

	private final UserDetailsService ud;

	public JWTAuthenticationFilter(JWTService js, UserDetailsService ud) {
		this.jwtService = js;
		this.ud = ud;

	}

	@Override
	protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
			@Nonnull FilterChain filterChain) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		jwt = authHeader.substring(7);
		
		
		userEmail = jwtService.extractUserName(jwt);
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails ud = this.ud.loadUserByUsername(userEmail);

			if (jwtService.isTokenValid(jwt, ud)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(ud, null,
						ud.getAuthorities());

				authToken.setDetails(

						new WebAuthenticationDetailsSource().buildDetails(request)

				);

				SecurityContextHolder.getContext().setAuthentication(authToken);

			}
		}
		
		filterChain.doFilter(request, response);

	}

}
