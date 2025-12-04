package com.reactionservice.crewproject.presentation.api.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;

/**
 * 회원 가입 Request Dto
 */

@Getter
public class SignUpRequestDto {
	/**
	 * 이메일 / 회원 id
	 */
	@NotBlank(message = "공백일 수 없습니다.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;

	/**
	 * 회원 Password
	 */
	@NotBlank(message = "공백일 수 없습니다.")
	@Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
	private String password;

	/**
	 * 회원 이름
	 */
	@NotBlank(message = "공백일 수 없습니다.")
	@Size(min = 2, max = 10, message = "2~10자 사이여야 합니다.")
	private String username;

	/**
	 * 휴대폰
	 */
	@NotBlank(message = "공백일 수 없습니다.")
	@Pattern(
		regexp = "^010-\\d{4}-\\d{4}$",
		message = "전화번호는 010-0000-0000 형식이어야 합니다."
	)
	private String phone;

}
