package com.reactionservice.crewproject.presentation.api.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원 가입 Response Dto
 */

@Getter
@AllArgsConstructor
public class SignUpResponseDto {

	/**
	 * 유저의 식별값
	 */
	private Long userId;

	public static SignUpResponseDto of(Long userId) {
		return new SignUpResponseDto(userId);
	}
}
