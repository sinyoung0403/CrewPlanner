package com.reactionservice.crewproject.presentation.api.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

/**
 * 로그인 Request Dto 입니다.
 */

@Getter
public class LoginRequestDto {

	/**
	 * 회원 이메일 
	 */
	
	@NotBlank(message = "공백일 수 없습니다.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;

	/**
	 * 회원 비밀번호
	 */
	
	@NotBlank(message = "공백일 수 없습니다.")
	private String password;
}
