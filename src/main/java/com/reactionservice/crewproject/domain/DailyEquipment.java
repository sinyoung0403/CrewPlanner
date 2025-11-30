package com.reactionservice.crewproject.domain;

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
 * 일일 장비 등록 테이블입니다.
 */

@Entity
@Table(name = "daily_equipments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyEquipment extends BaseTimeEntity {

	/**
	 * 식별값
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 스케쥴 / 1:N 관계
	 */

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	/**
	 * 장비 이름
	 */

	private String name;

	/**
	 * 장비 가격
	 */

	private Long price;

	/**
	 * 메모
	 */

	private String memo;
}
