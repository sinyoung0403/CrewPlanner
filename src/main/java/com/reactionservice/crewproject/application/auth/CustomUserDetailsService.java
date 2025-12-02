package com.reactionservice.crewproject.application.auth;

import java.util.Collection;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reactionservice.crewproject.application.common.exception.CustomException;
import com.reactionservice.crewproject.application.common.exception.ErrorCode;
import com.reactionservice.crewproject.domain.User;
import com.reactionservice.crewproject.infra.jpa.UserRepository;
import com.reactionservice.crewproject.presentation.api.security.CustomUserDetails;

/**
 * 사용자 로드 → UserDetails 로 변환
 */

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		// 1) 유저 조회
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

		// 2) CustomUserDetails 생성
		return CustomUserDetails.of(user.getId(), email);
	}
}
