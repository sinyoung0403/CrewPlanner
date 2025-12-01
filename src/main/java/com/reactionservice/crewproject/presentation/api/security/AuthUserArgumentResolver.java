package com.reactionservice.crewproject.presentation.api.security;

import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Request 에서 인증 객체와 Principal 을 가져와, AuthUser 를 반환
 */

@Component
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Auth.class)
			&& parameter.getParameterType().equals(AuthUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory) {

		// 1) Security Context 에서 Authentication 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			throw new AuthenticationCredentialsNotFoundException("Unauthenticated request");
		}

		// 2) Authentication 에서 Principal 가져오기
		Object principalObj = authentication.getPrincipal();
		if (!(principalObj instanceof CustomUserDetails principal)) {
			throw new AuthenticationCredentialsNotFoundException("Unsupported principal type");
		}

		// 3) AuthUser 객체 만들기
		return new AuthUser(principal.getUserId(), principal.getUsername());
	}
}
