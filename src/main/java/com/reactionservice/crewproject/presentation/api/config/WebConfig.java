package com.reactionservice.crewproject.presentation.api.config;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.reactionservice.crewproject.presentation.api.security.AuthUserArgumentResolver;

/**
 * 웹 구성 파일
 */

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final AuthUserArgumentResolver authUserArgumentResolver;

	/**
	 * 현재 로그인 유저 정보를 자동 주입하기 위한 Resolver 등록
	 * @param resolvers initially an empty list
	 */

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(authUserArgumentResolver);
	}
}
