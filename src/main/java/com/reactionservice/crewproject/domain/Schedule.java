package com.reactionservice.crewproject.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
 * 일정 테이블입니다.
 */

@Entity
@Table(name = "schedules")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseTimeEntity {

	/**
	 * 일정 식별자
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 사업장 - N:1 관계 (Fetch Type : Lazy)
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	/**
	 * 날짜
	 */
	private LocalDate date;

	/**
	 * 현장
	 */
	private String workplace;

	/**
	 * 작업 내용
	 */
	private String task;

	/**
	 * 메모
	 */
	private String memo;
}
