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
 * 사업장 테이블입니다.
 */
@Entity
@Table(name = "stores")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseTimeEntity {

	/**
	 * 업장 식별자
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 업장 주인
	 */
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;

	/**
	 * 업장명
	 */
	private String name;

	/**
	 * 주소
	 */
	private String address;

	/**
	 * 설명
	 */
	private String description;
}
