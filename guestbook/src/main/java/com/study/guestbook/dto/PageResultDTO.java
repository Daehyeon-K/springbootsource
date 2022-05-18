package com.study.guestbook.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// 페이지 결과처리를 위한 DTO
// 페이지 처리 결과 넘어오는 객체는 Page<Entity> 형태임
// 서비스 계층에서 넘어오는 형태의 변경을 위해서 사용

// <DTO, EN>에 대해
// Generic 개념, EN은 어떤 형식의 엔티티든, 엔티티만 던져주면 된다는 뜻.
// List<E>에서 E가 어떤 객체든 받는 의미인 것과 마찬가지로. 한글자 짜리 여러글자로 한 것
// 그냥 일단 인자로 PageRequestDTO 정해두면 그것밖에 못하니 이것 저것 못 씀. 분명 여러가지가 들어올 수 있는데 -> 보드, 멤버 등 아무거나 들어가짐

@Setter
@Getter
@Data
public class PageResultDTO<DTO,EN> {
	
	// 게시물 리스트
	private List<DTO> dtoList;
	
	// 현재 페이지 번호
	private int page;
	
	// 목록 사이즈
	private int size;
	
	// 시작 페이지 번호
	private int start;
	// 끝 페이지 번호
	private int end;
	
	// 이전, 다음
	private boolean prev, next;
	
	// 페이지 번호 목록
	private List<Integer> pageList;
	
	// 총 페이지 번호
	private int totalPage;
	
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		totalPage=result.getTotalPages();
		makePageList(result.getPageable());
	}
	
	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber()+1;
		this.size = pageable.getPageSize();
		
		int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
		start = tempEnd - 9;
		
		prev = start>1;
		end = totalPage > tempEnd ? tempEnd:totalPage;
		next = totalPage > tempEnd;
		
		// boxed는 int를 Integer 형태로 바꿔주는 그런
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
}
