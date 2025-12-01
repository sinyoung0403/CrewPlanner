package com.reactionservice.crewproject.presentation.api.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.reactionservice.crewproject.presentation.api.security.JwtFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtFilter jwtFilter;

	/**
	 * Chain Filter Bean 등록
	 *
	 * @param httpSecurity
	 * @return SecurityFilterChain
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors(cors -> {})
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
				.anyRequest().authenticated()
			)

			// 세션을 STATELESS
			.sessionManagement(session ->
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)

			// UsernamePasswordAuthenticationFilter 이전에 Jwt Filter 추가
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}

	/**
	 * Password Encoder Bean 등록
	 *
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
