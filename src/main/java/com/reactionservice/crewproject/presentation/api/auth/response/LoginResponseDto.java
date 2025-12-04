package com.reactionservice.crewproject.presentation.api.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인 Response dto 입니다.
 */

@Getter
@AllArgsConstructor
public class LoginResponseDto {

	/**
	 * 액세스 토큰
	 */
	private String accessToken;

	/**
	 * 리프레시 토큰
	 */
	private String refreshToken;

	/**
	 * Login Response Dto로 만들기 위한 정적 메서드
	 * @param accessToken
	 * @param refreshToken
	 * @return LoginResponseDto
	 */

	public static LoginResponseDto of(String accessToken, String refreshToken) {
		return new LoginResponseDto(accessToken, refreshToken);
	}
}
