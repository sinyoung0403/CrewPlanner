package com.reactionservice.crewproject.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.reactionservice.crewproject.domain.common.BaseTimeEntity;

/**
 * 직원 일정 테이블입니다.
 */

@Entity
@Table(name = "employee_schedules")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeSchedule extends BaseTimeEntity {

	/**
	 * 직원 일정 식별자
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 일정 - N:1 관계
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	/**
	 * 직원 - N:1관계
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	/**
	 * 작업 시간
	 */
	@Column(nullable = false)
	private double workUnits;

	/**
	 * 메모
	 */
	private String memo;
}
