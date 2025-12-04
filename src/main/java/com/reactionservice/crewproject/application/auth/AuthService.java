package com.reactionservice.crewproject.application.auth;

import java.util.concurrent.TimeUnit;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reactionservice.crewproject.application.common.exception.CustomException;
import com.reactionservice.crewproject.application.common.exception.ErrorCode;
import com.reactionservice.crewproject.domain.User;
import com.reactionservice.crewproject.infra.jpa.UserRepository;
import com.reactionservice.crewproject.infra.security.JwtTokenProvider;
import com.reactionservice.crewproject.presentation.api.auth.request.LoginRequestDto;
import com.reactionservice.crewproject.presentation.api.auth.request.SignUpRequestDto;
import com.reactionservice.crewproject.presentation.api.auth.response.LoginResponseDto;
import com.reactionservice.crewproject.presentation.api.auth.response.SignUpResponseDto;
import com.reactionservice.crewproject.presentation.api.security.JwtTokenResolver;

/**
 * 인증을 위한 서비스 로직입니다.
 */

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtTokenResolver jwtTokenResolver;
	private final RedisTemplate redisTemplate;

	/**
	 * 회원가입
	 *
	 * @param signUpRequestDto
	 * @return SignUpResponseDto
	 */
	@Transactional
	public SignUpResponseDto signup(@Valid SignUpRequestDto signUpRequestDto) {
		// 1) 중복 조회
		userRepository.findByEmail(signUpRequestDto.getEmail())
			.ifPresent(user -> {
				throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
			});

		// 2) 패스워드 암호화
		String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());

		// 3) 유저 생성
		User user = new User(
			signUpRequestDto.getEmail(),
			encodedPassword,
			signUpRequestDto.getUsername(),
			signUpRequestDto.getEmail()
		);

		// 5) 유저 저장
		userRepository.save(user);

		// 6) DTO 반환
		return SignUpResponseDto.of(user.getId());
	}

	/**
	 * 로그인
	 *
	 * @param loginRequestDto
	 * @return AuthResponse (AccessToken 과 RefreshToken 반환)
	 */
	@Transactional
	public LoginResponseDto login(@Valid LoginRequestDto loginRequestDto) {
		// 1) User Email 일치 여부 확인
		User findUser = userRepository.findByEmail(loginRequestDto.getEmail())
			.orElseThrow(() -> new CustomException(ErrorCode.LOGIN_FAILED));

		// 2) pwd 일치 여부 확인
		if (!passwordEncoder.matches(loginRequestDto.getPassword(), findUser.getPassword())) {
			throw new CustomException(ErrorCode.LOGIN_FAILED);
		}

		// 3) Token 발급
		String accessToken = jwtTokenProvider.generateAccessToken(findUser.getId(), findUser.getEmail());
		String refreshToken = jwtTokenProvider.generateRefreshToken(findUser.getId(), findUser.getEmail());

		// 4) Refresh Token 저장 - Redis 에 저장
		redisTemplate.opsForValue().set(
			"rt:" + refreshToken,
			"login",
			JwtTokenProvider.REFRESH_TOKEN_EXPIRATION_MS / 1000,
			TimeUnit.SECONDS
		);

		// 5) DTO 반환
		return LoginResponseDto.of(accessToken, refreshToken);
	}
}
