package com.reactionservice.crewproject.presentation.api.store;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reactionservice.crewproject.application.store.StoreService;
import com.reactionservice.crewproject.presentation.api.common.response.BaseResponse;
import com.reactionservice.crewproject.presentation.api.common.response.PageResponse;
import com.reactionservice.crewproject.presentation.api.security.Auth;
import com.reactionservice.crewproject.presentation.api.security.AuthUser;
import com.reactionservice.crewproject.presentation.api.store.request.CreateStoreRequestDto;
import com.reactionservice.crewproject.presentation.api.store.request.UpdateStoreRequestDto;
import com.reactionservice.crewproject.presentation.api.store.response.CreateStoreResponseDto;
import com.reactionservice.crewproject.presentation.api.store.response.InfoStoreResponseDto;

/**
 * 사업장의 정보를 조회 및 수정하는 API Controller 입니다.
 */

@RequestMapping("/api/store")
@RequiredArgsConstructor
@RestController
public class StoreController {

	private final StoreService storeService;

	/**
	 * 새로운 사업장을 생성
	 *
	 * @param createStoreRequestDto 사업장 생성 정보
	 * @param authUser              로그인한 유저 정보
	 * @return 생성된 사업장 정보를 포함한 응답
	 */

	@PostMapping
	public ResponseEntity<BaseResponse<CreateStoreResponseDto>> CreateStore(
		@Valid @RequestBody CreateStoreRequestDto createStoreRequestDto,
		@Auth AuthUser authUser
	) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(BaseResponse.success(storeService.createStore(createStoreRequestDto, authUser.userId())));
	}

	/**
	 * 로그인한 유저의 특정 사업장 조회
	 *
	 * @param authUser 로그인한 유저 정보
	 * @param storeId  사업장 식별값
	 * @return 사업장 정보
	 */

	@GetMapping("/storeId")
	public ResponseEntity<BaseResponse<InfoStoreResponseDto>> findById(
		@Auth AuthUser authUser,
		@PathVariable Long storeId
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponse.success(storeService.findById(authUser.userId(), storeId)));

	}

	/**
	 * 로그인한 유저의 모든 사업장 조회
	 *
	 * @param authUser 로그인한 사용자 정보
	 * @param page     조회할 페이지 번호
	 * @param size     한 페이지 당 데이터 개수
	 * @return 사업장 목록 페이징 결과
	 */

	@GetMapping
	public ResponseEntity<BaseResponse<PageResponse<InfoStoreResponseDto>>> findAll(
		@Auth AuthUser authUser,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "5") int size
	) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(BaseResponse.success(PageResponse.of(storeService.findAll(page, size, authUser.userId()))));
	}

	/**
	 * 사업장 수정
	 *
	 * @param authUser              로그인한 사용자 정보
	 * @param storeId               사업장 식별값
	 * @param updateStoreRequestDto 수정할 사업장 정보
	 * @return 없음
	 */

	@PatchMapping("/{storeId}")
	public ResponseEntity<BaseResponse<Void>> updateStore(
		@Auth AuthUser authUser,
		@PathVariable Long storeId,
		UpdateStoreRequestDto updateStoreRequestDto
	) {
		storeService.updateStore(authUser.userId(), storeId, updateStoreRequestDto);
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponse.success(null));
	}

	/**
	 * 사업장 삭제
	 *
	 * @param authUser 로그인한 사용자 정보
	 * @param storeId  사업장 식별값
	 * @return 없음
	 */

	@DeleteMapping("/{storeId}")
	public ResponseEntity<BaseResponse<Void>> deleteStore(
		@Auth AuthUser authUser,
		@PathVariable Long storeId
	) {
		storeService.deleteStore(authUser.userId(), storeId);
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponse.success(null));
	}
}
