package com.reactionservice.crewproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 테이블
 */

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	/**
	 * 회원 이름
	 */
	private String name;

	/**
	 * 회원 이메일 | ID 와 같은 역할
	 */
	private String email;

	/**
	 * 회원 비밀번호
	 */
	private String password;

	/**
	 * 휴대폰 번호 -> 000-0000-0000 형식
	 */
	private String phone;

}
