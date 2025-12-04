package com.reactionservice.crewproject.presentation.api.auth;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactionservice.crewproject.application.auth.AuthService;
import com.reactionservice.crewproject.presentation.api.auth.request.LoginRequestDto;
import com.reactionservice.crewproject.presentation.api.auth.request.SignUpRequestDto;
import com.reactionservice.crewproject.presentation.api.auth.response.LoginResponseDto;
import com.reactionservice.crewproject.presentation.api.auth.response.SignUpResponseDto;
import com.reactionservice.crewproject.presentation.api.common.response.BaseResponse;

/**
 * 인증 관련된 API 컨트롤러 입니다.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	/**
	 * 회원 가입
	 *
	 * @param signUpRequestDto
	 * @return ResponseEntity<BaseResponse < SignUpResponseDto>>
	 */

	@PostMapping("/signup")
	public ResponseEntity<BaseResponse<SignUpResponseDto>> signup(
		@Valid @RequestBody SignUpRequestDto signUpRequestDto
	) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(BaseResponse.success(authService.signup(signUpRequestDto)));
	}

	/**
	 * 로그인
	 *
	 * @param loginRequestDto
	 * @return ResponseEntity<BaseResponse < LoginResponseDto>>
	 */

	@PostMapping("/login")
	public ResponseEntity<BaseResponse<LoginResponseDto>> login(
		@Valid @RequestBody LoginRequestDto loginRequestDto
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponse.success(authService.login(loginRequestDto)));
	}

	// Todo: 추후에 로그아웃을 구현할 예정입니다.
	@PostMapping("/logout")
	public ResponseEntity<BaseResponse<Void>> logout() {
		return null;
	}
}
