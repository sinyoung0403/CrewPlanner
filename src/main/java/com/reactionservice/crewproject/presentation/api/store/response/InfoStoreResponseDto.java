package com.reactionservice.crewproject.presentation.api.store.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자 정보를 담은 Response Dto 입니다.
 */

@Getter
@AllArgsConstructor
public class InfoStoreResponseDto {

	/**
	 * 업장 식별값
	 */
	private Long storeId;

	/**
	 * 업장명
	 */
	private String storeName;

	/**
	 * 업장 주소
	 */
	private String storeAddress;

	/**
	 * 업장 설명
	 */
	private String storeDescription;

	public static InfoStoreResponseDto of(Long storeId, String storeName, String storeAddress,
		String storeDescription) {
		return new InfoStoreResponseDto(storeId, storeName, storeAddress, storeDescription);
	}
}
