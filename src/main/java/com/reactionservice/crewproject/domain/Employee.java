package com.reactionservice.crewproject.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.reactionservice.crewproject.domain.common.BaseTimeEntity;

/**
 * 직원 테이블입니다.
 */
@Entity
@Table(name = "employees")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends BaseTimeEntity {

	/**
	 * 직원 식별자
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 사업장 - N:1 관계 = Eager Fetch
	 */
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	/**
	 * 직원 이름
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * 생년월일
	 */
	@Column(nullable = false)
	private LocalDate brith;

	/**
	 * 휴대폰 번호
	 */
	@Column(nullable = false)
	private String phone;

	/**
	 * 지역
	 */
	private String area;

	/**
	 * 일급
	 */
	private Integer dailyWage;

	/**
	 * 시급
	 */
	private Integer hourWage;
}
