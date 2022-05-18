package com.study.guestbook.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 화면에서 전달되는 목록 데이터 DTO
// 페이지 번호, 페이지 내 보여줄 목록 수, 검색타입, 검색어 => Criteria와 비슷한 객체

@AllArgsConstructor
@Builder
@Data
public class PageRequestDTO {
	
	private int page; // 현재 페이지 번호
	private int size; // 페이지 내 보여줄 목록 수
//	private String ; // 검색 타입
//	private String ; // 검색어
	
	public PageRequestDTO() {
		this.page=1;
		this.size=10;
	}
	
	public Pageable getPageable(Sort sort) {
		
		// 시작 페이지 0이기 때문에 -1 해줌
		return PageRequest.of(page-1, size, sort);
	}
	
	
	
}
